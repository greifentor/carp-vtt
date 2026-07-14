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
import de.ollie.carp.vtt.core.service.model.event.TokenPositionUpdateEvent;
import de.ollie.carp.vtt.core.service.port.web.TokenWebPort;
import de.ollie.carp.vtt.swing.TokenMap.MapToken;
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
	private final transient TokenPositionService tokenPositionService;
	private final transient TokenService tokenService;
	private final transient TokenWebPort tokenWebPort;
	private final transient UuidService uuidService;

	private JButton buttonAddIcon = new JButton("+");
	private JComboBox<BattleMap> comboBoxBattleMaps;
	private JPanel panelImage;
	private MapPanel battleMapPanel;
	private Token selectedToken;
	private TokenMap tokens = new TokenMap();

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
		buttonAddIcon.addActionListener(this);
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
		} else if (e.getSource() == comboBoxBattleMaps) {
			panelImage.removeAll();
			try {
				Image image = ImageIO.read(
					new ByteArrayInputStream((((BattleMap) comboBoxBattleMaps.getSelectedItem()).getImageContent()))
				);
				ImageIcon imageIcon = new ImageIcon(image);
				battleMapPanel = new MapPanel(imageIcon, tokens, this);
				battleMapPanel.addMouseListener(
					new MouseAdapter() {
						@Override
						public void mouseClicked(MouseEvent e) {
							if (selectedToken != null) {
								MapToken newMapToken = new MapToken(
									selectedToken,
									tokens.getNextCounterFor(selectedToken),
									uuidService.create()
								);
								System.out.println(newMapToken);
								battleMapPanel.setSelectedToken(newMapToken);
								updatePosition(getFieldCoordinates(e.getX(), e.getY()));
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
		tokenData.forEach(td ->
			tokens.put(new MapToken(td.getToken(), tokens.getNextCounterFor(td.getToken()), td.getId()), td.getCoordinates())
		);
		return tokens;
	}

	private void updatePosition(Coordinates coordinates) {
		TokenPositionUpdateEvent event = new TokenPositionUpdateEvent(
			battleMapPanel.getSelectedToken().id(),
			battleMapPanel.getSelectedToken().token(),
			(BattleMap) comboBoxBattleMaps.getSelectedItem(),
			coordinates,
			DUMMY_PARTY,
			DUMMY_SCENARIO
		);
		tokenPositionService.updateTokenPosition(event);
		tokenWebPort.pushTokenPositionUpdate(event);
		tokens.put(battleMapPanel.getSelectedToken(), coordinates);
		battleMapPanel.updateTokens(tokens);
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
			} else {
				battleMapPanel.setSelectedToken(mapToken);
			}
		} else if (battleMapPanel.getSelectedToken() != null) {
			updatePosition(coordinates);
		}
	}
}
