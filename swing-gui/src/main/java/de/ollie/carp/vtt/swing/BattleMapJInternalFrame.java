package de.ollie.carp.vtt.swing;

import static de.ollie.carp.vtt.swing.SwingConstants.HGAP;
import static de.ollie.carp.vtt.swing.SwingConstants.VGAP;

import de.ollie.carp.vtt.core.service.DndTokenDataService;
import de.ollie.carp.vtt.core.service.DndTokenMapDataService;
import de.ollie.carp.vtt.core.service.PartyService;
import de.ollie.carp.vtt.core.service.TokenPositionService;
import de.ollie.carp.vtt.core.service.TokenService;
import de.ollie.carp.vtt.core.service.UuidService;
import de.ollie.carp.vtt.core.service.model.BattleMapPartyScenario;
import de.ollie.carp.vtt.core.service.model.Coordinates;
import de.ollie.carp.vtt.core.service.model.CoordinatesInfoProvider;
import de.ollie.carp.vtt.core.service.model.DndTokenData;
import de.ollie.carp.vtt.core.service.model.DndTokenMapData;
import de.ollie.carp.vtt.core.service.model.Party;
import de.ollie.carp.vtt.core.service.model.PartyImpl;
import de.ollie.carp.vtt.core.service.model.RuleSystem;
import de.ollie.carp.vtt.core.service.model.Scenario;
import de.ollie.carp.vtt.core.service.model.ScenarioImpl;
import de.ollie.carp.vtt.core.service.model.Token;
import de.ollie.carp.vtt.core.service.model.TokenMapData;
import de.ollie.carp.vtt.core.service.model.event.TokenPositionRemoveEvent;
import de.ollie.carp.vtt.core.service.model.event.TokenPositionUpdateEvent;
import de.ollie.carp.vtt.core.service.port.web.TokenPositionWebPort;
import de.ollie.carp.vtt.core.service.port.web.TokenWebPort;
import de.ollie.carp.vtt.graphics.manager.GraphicsManager;
import de.ollie.carp.vtt.graphics.manager.model.TokenMap;
import de.ollie.carp.vtt.graphics.manager.model.TokenMap.MapToken;
import de.ollie.carp.vtt.graphics.manager.model.TokenMap.MapTokenId;
import de.ollie.carp.vtt.graphics.manager.model.TokenPaintManager;
import de.ollie.carp.vtt.swing.component.TokenSelectionDialog;
import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;
import java.util.UUID;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JToolBar;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BattleMapJInternalFrame extends JInternalFrame implements ActionListener, MapPanel.Observer {

	private static final Party DUMMY_PARTY = new PartyImpl();
	private static final Scenario DUMMY_SCENARIO = new ScenarioImpl();

	private final JDesktopPane desktopPane;
	private final BattleMapPartyScenario battleMapPartyScenario;
	private final transient CoordinatesToFieldConverter coordinatesToFieldConverter;
	private final transient DndTokenDataService dndTokenDataService;
	private final transient DndTokenMapDataService dndTokenMapDataService;
	private final transient GraphicsManager graphicsManager;
	private final transient PartyService partyService;
	private final transient TokenHitManager tokenHitManager;
	private final transient TokenPositionService tokenPositionService;
	private final transient TokenService tokenService;
	private final transient TokenPositionWebPort tokenPositionWebPort;
	private final transient TokenWebPort tokenWebPort;
	private final transient UuidService uuidService;

	private JButton buttonAddIcon = new JButton("+");
	private JButton buttonDamageIcon = new JButton("Damage");
	private JButton buttonRemoveIcon = new JButton("-");
	private JPanel panelImage;
	private MapPanel battleMapPanel;
	private transient Token selectedToken;
	private transient TokenMap tokenMap = new TokenMap();

	public BattleMapJInternalFrame prepare() {
		DUMMY_PARTY.setId(UUID.fromString("d95b7312-5669-4ee5-9299-4516034f46d8"));
		DUMMY_PARTY.setRuleSystem(RuleSystem.NONE);
		DUMMY_SCENARIO.setId(UUID.fromString("60d8b44d-f60f-4b04-b9bb-133b3335db0f"));
		desktopPane.add(this);
		setClosable(true);
		setIconifiable(true);
		setMaximizable(true);
		setResizable(true);
		setBounds(50, 50, 640, 480);
		setContentPane(createMainPanel());
		pack();
		panelImage.removeAll();
		try {
			Image image = ImageIO.read(new ByteArrayInputStream(battleMapPartyScenario.getBattleMap().getImageContent()));
			ImageIcon imageIcon = new ImageIcon(image);
			battleMapPanel =
				new MapPanel(imageIcon, tokenMap, this, graphicsManager, tokenHitManager, coordinatesToFieldConverter);
			battleMapPanel.addMouseListener(
				new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						if (selectedToken != null) {
							MapToken newMapToken = new MapToken(
								selectedToken,
								tokenMap.getNextCounterFor(selectedToken),
								new MapTokenId(uuidService.create()),
								true,
								null
							);
							battleMapPanel.setSelectedToken(newMapToken);
							tokenMap.put(newMapToken.getId(), newMapToken);
							DndTokenData dtd = dndTokenDataService.findById(newMapToken.getToken().getId()).get();
							DndTokenMapData dtbmps = dndTokenMapDataService.getByBattleMapPartyScenarioToken(
								battleMapPartyScenario.getBattleMap().getId(),
								DUMMY_PARTY.getId(),
								DUMMY_SCENARIO.getId(),
								newMapToken.getToken().getId()
							);
							dtbmps.setCurrentHitPoints(dtd.getHitPoints());
							updatePosition(newMapToken, coordinatesToFieldConverter.getFieldCoordinates(e.getX(), e.getY()), true);
							selectedToken = null;
						}
					}
				}
			);
			panelImage.add(new JScrollPane(battleMapPanel), BorderLayout.CENTER);
			setBounds(getX(), getY(), imageIcon.getIconWidth(), imageIcon.getIconHeight());
			List<TokenMapData> storedTokens = tokenPositionService.findAllBy(
				battleMapPartyScenario.getBattleMap(),
				DUMMY_PARTY,
				DUMMY_SCENARIO
			);
			battleMapPanel.updateTokens(map(storedTokens));
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}

		return this;
	}

	private JPanel createMainPanel() {
		JPanel p = new JPanel(new BorderLayout(HGAP, VGAP));
		panelImage = new JPanel(new BorderLayout(HGAP, VGAP));
		JToolBar toolbar = new JToolBar(javax.swing.SwingConstants.VERTICAL);
		toolbar.setFloatable(false);
		toolbar.add(buttonAddIcon);
		toolbar.add(buttonRemoveIcon);
		toolbar.add(new JSeparator());
		toolbar.add(buttonDamageIcon);
		buttonAddIcon.addActionListener(this);
		buttonDamageIcon.addActionListener(this);
		buttonRemoveIcon.addActionListener(this);
		p.add(toolbar, BorderLayout.WEST);
		p.add(panelImage, BorderLayout.CENTER);
		return p;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == buttonAddIcon) {
			selectedToken = new TokenSelectionDialog(null, tokenService.listTokens()).getSelectedToken();
			battleMapPanel.setSelectedToken(null);
		} else if ((e.getSource() == buttonDamageIcon) && (battleMapPanel.getSelectedToken() != null)) {
			updateHitPoints(battleMapPanel.getSelectedToken());
		} else if ((e.getSource() == buttonRemoveIcon) && (battleMapPanel.getSelectedToken() != null)) {
			MapTokenId toDelete = battleMapPanel.getSelectedToken().getId();
			tokenMap.remove(toDelete);
			tokenPositionService.delete(toDelete.getUuid());
			tokenPositionWebPort.pushTokenPositionRemove(new TokenPositionRemoveEvent(toDelete.getUuid()));
			battleMapPanel.updateTokens(tokenMap);
			battleMapPanel.setSelectedToken(null);
		}
	}

	private TokenMap map(List<TokenMapData> tokenData) {
		tokenMap.clear();
		tokenData.forEach(td -> {
			MapTokenId id = new MapTokenId(td.getId());
			TokenPaintManager tokenPaintManager = null;
			if (DUMMY_PARTY.getRuleSystem() == RuleSystem.DND) {
				// TODO
			}
			tokenMap.put(
				id,
				new MapToken(td.getToken(), td.getCounter(), id, td.isSelected(), td.getCoordinates(), tokenPaintManager)
			);
		});
		return tokenMap;
	}

	private void updatePosition(MapToken mapToken, CoordinatesInfoProvider coordinates, boolean selected) {
		TokenPositionUpdateEvent event = new TokenPositionUpdateEvent(
			mapToken.getId().getUuid(),
			(Token) battleMapPanel.getSelectedToken().getToken(),
			battleMapPartyScenario.getBattleMap(),
			mapToken.getCounter(),
			coordinates,
			DUMMY_PARTY,
			DUMMY_SCENARIO,
			selected
		);
		tokenPositionService.updateTokenPosition(event);
		tokenPositionWebPort.pushTokenPositionUpdate(event);
		tokenMap.putCoordinates(battleMapPanel.getSelectedToken().getId(), coordinates);
		battleMapPanel.updateTokens(tokenMap);
	}

	private void updateHitPoints(MapToken mapToken) {
		// TokenPositionUpdateEvent event = new TokenPositionUpdateEvent(
		// mapToken.getId().getUuid(),
		// (Token) battleMapPanel.getSelectedToken().getToken(),
		// (BattleMap) comboBoxBattleMaps.getSelectedItem(),
		// mapToken.getCounter(),
		// coordinates,
		// DUMMY_PARTY,
		// DUMMY_SCENARIO,
		// selected
		// );
		if (DUMMY_PARTY.getRuleSystem() == RuleSystem.DND) {
			// DndTokenPaintManager dtpm = (DndTokenPaintManager)
			// mapToken.getTokenPaintManager();
			// dtpm.decreaseHitPoints(10);
			// dtpm.setDtbmps(dndTokenBattleMapPartyScenarioService.update(dtpm.getDtbmps()));
		}
		// tokenWebPort.pushTokenPositionUpdate(event);
		battleMapPanel.updateTokens(tokenMap);
	}

	@Override
	public void tokenHit(MapToken mapToken, Coordinates coordinates) {
		if (mapToken != null) {
			if (mapToken == battleMapPanel.getSelectedToken()) {
				battleMapPanel.setSelectedToken(null);
				unselectSelectedToken();
			} else {
				unselectSelectedToken();
				battleMapPanel.setSelectedToken(mapToken);
				updatePosition(mapToken, coordinates, true);
			}
		} else if (battleMapPanel.getSelectedToken() != null) {
			unselectSelectedToken();
			updatePosition(battleMapPanel.getSelectedToken(), coordinates, true);
		}
	}

	private void unselectSelectedToken() {
		tokenPositionWebPort.unselect(
			battleMapPartyScenario.getBattleMap().getId(),
			battleMapPartyScenario.getParty().getId(),
			battleMapPartyScenario.getScenario().getId()
		);
	}
}
