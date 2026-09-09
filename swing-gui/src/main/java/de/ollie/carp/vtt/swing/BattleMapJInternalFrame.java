package de.ollie.carp.vtt.swing;

import static de.ollie.carp.vtt.swing.SwingConstants.HGAP;
import static de.ollie.carp.vtt.swing.SwingConstants.VGAP;

import de.ollie.carp.vtt.core.service.BattleMapService;
import de.ollie.carp.vtt.core.service.TokenPositionService;
import de.ollie.carp.vtt.core.service.TokenService;
import de.ollie.carp.vtt.core.service.UuidService;
import de.ollie.carp.vtt.core.service.model.BattleMap;
import de.ollie.carp.vtt.core.service.model.Coordinates;
import de.ollie.carp.vtt.core.service.model.Party;
import de.ollie.carp.vtt.core.service.model.Scenario;
import de.ollie.carp.vtt.core.service.model.Token;
import de.ollie.carp.vtt.core.service.model.TokenData;
import de.ollie.carp.vtt.core.service.model.event.TokenPositionRemoveEvent;
import de.ollie.carp.vtt.core.service.model.event.TokenPositionUpdateEvent;
import de.ollie.carp.vtt.core.service.port.web.TokenWebPort;
import de.ollie.carp.vtt.graphics.manager.GraphicsManager;
import de.ollie.carp.vtt.graphics.manager.model.TokenMap;
import de.ollie.carp.vtt.graphics.manager.model.TokenMap.MapToken;
import de.ollie.carp.vtt.graphics.manager.model.TokenMap.MapTokenId;
import de.ollie.carp.vtt.swing.component.TokenSelectionDialog;
import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BattleMapJInternalFrame extends JInternalFrame implements ActionListener, MapPanel.Observer {

	private static final Party DUMMY_PARTY = new Party().setId(UUID.fromString("d95b7312-5669-4ee5-9299-4516034f46d8"));
	private static final Scenario DUMMY_SCENARIO = new Scenario()
		.setId(UUID.fromString("60d8b44d-f60f-4b04-b9bb-133b3335db0f"));

	private final JDesktopPane desktopPane;
	private final transient BattleMapService mapService;
	private final transient GraphicsManager graphicsManager;
	private final transient TokenHitManager tokenHitManager;
	private final transient TokenPositionService tokenPositionService;
	private final transient TokenService tokenService;
	private final transient TokenWebPort tokenWebPort;
	private final transient UuidService uuidService;

	private JButton buttonAddIcon = new JButton("+");
	private JButton buttonRemoveIcon = new JButton("-");
	private JComboBox<BattleMap> comboBoxBattleMaps;
	private JPanel panelImage;
	private MapPanel battleMapPanel;
	private Token selectedToken;
	private TokenMap tokenMap = new TokenMap();

	public BattleMapJInternalFrame prepare() {
		desktopPane.add(this);
		setClosable(true);
		setIconifiable(true);
		setMaximizable(true);
		setResizable(true);
		setBounds(50, 50, 640, 480);
		setContentPane(createMainPanel());
		pack();
		return this;
	}

	private JPanel createMainPanel() {
		JPanel p = new JPanel(new BorderLayout(HGAP, VGAP));
		comboBoxBattleMaps = createMapsComboBox();
		comboBoxBattleMaps.setRenderer(new BattleMapListCellRenderer());
		comboBoxBattleMaps.addActionListener(this);
		panelImage = new JPanel(new BorderLayout(HGAP, VGAP));
		JToolBar toolbar = new JToolBar(JToolBar.VERTICAL);
		toolbar.setFloatable(false);
		toolbar.add(buttonAddIcon);
		toolbar.add(buttonRemoveIcon);
		buttonAddIcon.addActionListener(this);
		buttonRemoveIcon.addActionListener(this);
		p.add(toolbar, BorderLayout.WEST);
		p.add(comboBoxBattleMaps, BorderLayout.NORTH);
		p.add(panelImage, BorderLayout.CENTER);
		return p;
	}

	private JComboBox<BattleMap> createMapsComboBox() {
		return new JComboBox<>(mapService.findAll().toArray(new BattleMap[0]));
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == buttonAddIcon) {
			selectedToken = new TokenSelectionDialog(null, tokenService.findAll()).getSelectedToken();
			battleMapPanel.setSelectedToken(null);
		} else if ((e.getSource() == buttonRemoveIcon) && (battleMapPanel.getSelectedToken() != null)) {
			MapTokenId toDelete = battleMapPanel.getSelectedToken().getId();
			tokenMap.remove(toDelete);
			tokenPositionService.delete(toDelete.getUuid());
			tokenWebPort.pushTokenPositionRemove(new TokenPositionRemoveEvent(toDelete.getUuid()));
			battleMapPanel.updateTokens(tokenMap);
			battleMapPanel.setSelectedToken(null);
		} else if (e.getSource() == comboBoxBattleMaps) {
			panelImage.removeAll();
			try {
				Image image = ImageIO.read(
					new ByteArrayInputStream((((BattleMap) comboBoxBattleMaps.getSelectedItem()).getImageContent()))
				);
				ImageIcon imageIcon = new ImageIcon(image);
				battleMapPanel = new MapPanel(imageIcon, tokenMap, this, graphicsManager, tokenHitManager);
				battleMapPanel.addMouseListener(
					new MouseAdapter() {
						@Override
						public void mouseClicked(MouseEvent e) {
							if (selectedToken != null) {
								MapToken newMapToken = new MapToken(
									selectedToken,
									tokenMap.getNextCounterFor(selectedToken),
									new MapTokenId(uuidService.create()),
									true
								);
								battleMapPanel.setSelectedToken(newMapToken);
								tokenMap.put(newMapToken.getId(), newMapToken);
								updatePosition(newMapToken, getFieldCoordinates(e.getX(), e.getY()), true);
								selectedToken = null;
							}
						}
					}
				);
				panelImage.add(new JScrollPane(battleMapPanel), BorderLayout.CENTER);
				setBounds(getX(), getY(), imageIcon.getIconWidth(), imageIcon.getIconHeight());
				List<TokenData> storedTokens = tokenPositionService.findAllBy(
					(BattleMap) comboBoxBattleMaps.getSelectedItem(),
					DUMMY_PARTY,
					DUMMY_SCENARIO
				);
				battleMapPanel.updateTokens(map(storedTokens));
			} catch (IOException ioe) {
				ioe.printStackTrace();
			}
		}
	}

	private TokenMap map(List<TokenData> tokenData) {
		tokenMap.clear();
		tokenData.forEach(td -> {
			MapTokenId id = new MapTokenId(td.getId());
			tokenMap.put(id, new MapToken(td.getToken(), td.getCounter(), id, td.isSelected(), td.getCoordinates()));
		});
		return tokenMap;
	}

	private void updatePosition(MapToken mapToken, Coordinates coordinates, boolean selected) {
		TokenPositionUpdateEvent event = new TokenPositionUpdateEvent(
			mapToken.getId().getUuid(),
			(Token) battleMapPanel.getSelectedToken().getToken(),
			(BattleMap) comboBoxBattleMaps.getSelectedItem(),
			mapToken.getCounter(),
			coordinates,
			DUMMY_PARTY,
			DUMMY_SCENARIO,
			selected
		);
		tokenPositionService.updateTokenPosition(event);
		tokenWebPort.pushTokenPositionUpdate(event);
		tokenMap.putCoordinates(battleMapPanel.getSelectedToken().getId(), coordinates);
		battleMapPanel.updateTokens(tokenMap);
	}

	private static final int OFFSET_IN_PIXELS = 25;
	private static final int FIELD_SIZE_IN_PIXELS = 50;

	public Coordinates getFieldCoordinates(int x, int y) {
		String fieldX = ((x - OFFSET_IN_PIXELS) / FIELD_SIZE_IN_PIXELS) + ".0";
		String fieldY = ((y - OFFSET_IN_PIXELS) / FIELD_SIZE_IN_PIXELS) + ".0";
		return new Coordinates().setFieldX(new BigDecimal(fieldX)).setFieldY(new BigDecimal(fieldY));
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
		tokenWebPort.unselect(
			((BattleMap) comboBoxBattleMaps.getSelectedItem()).getId(),
			DUMMY_PARTY.getId(),
			DUMMY_SCENARIO.getId()
		);
	}
}
