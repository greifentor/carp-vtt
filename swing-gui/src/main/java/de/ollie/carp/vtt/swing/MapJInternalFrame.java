package de.ollie.carp.vtt.swing;

import static de.ollie.carp.vtt.swing.SwingConstants.HGAP;
import static de.ollie.carp.vtt.swing.SwingConstants.VGAP;

import de.ollie.carp.vtt.core.service.MapService;
import de.ollie.carp.vtt.core.service.TokenService;
import de.ollie.carp.vtt.core.service.model.Coordinates;
import de.ollie.carp.vtt.core.service.model.Map;
import de.ollie.carp.vtt.core.service.model.Token;
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
import java.util.HashMap;
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
public class MapJInternalFrame extends JInternalFrame implements ActionListener, MapPanel.Observer {

	private final JDesktopPane desktopPane;
	private final transient MapService mapService;
	private final transient TokenService tokenService;

	private JButton buttonAddIcon = new JButton("+");
	private JComboBox<Map> comboBoxMaps;
	private JPanel panelImage;
	private MapPanel mapPanel;
	private Token selectedToken;
	private java.util.Map<Token, Coordinates> tokens = new HashMap<>();

	public MapJInternalFrame prepare() {
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
		comboBoxMaps = createMapsComboBox();
		comboBoxMaps.setRenderer(new MapListCellRenderer());
		comboBoxMaps.addActionListener(this);
		panelImage = new JPanel(new BorderLayout(HGAP, VGAP));
		JToolBar toolbar = new JToolBar(JToolBar.VERTICAL);
		toolbar.setFloatable(false);
		toolbar.add(buttonAddIcon);
		buttonAddIcon.addActionListener(this);
		p.add(toolbar, BorderLayout.WEST);
		p.add(comboBoxMaps, BorderLayout.NORTH);
		p.add(panelImage, BorderLayout.CENTER);
		return p;
	}

	private JComboBox<Map> createMapsComboBox() {
		return new JComboBox<>(mapService.findAll().toArray(new Map[0]));
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == buttonAddIcon) {
			selectedToken = new TokenSelectionDialog(null, tokenService.findAll()).getSelectedToken();
		} else if (e.getSource() == comboBoxMaps) {
			panelImage.removeAll();
			try {
				Image image = ImageIO.read(new ByteArrayInputStream((((Map) comboBoxMaps.getSelectedItem()).getImage())));
				ImageIcon imageIcon = new ImageIcon(image);
				mapPanel = new MapPanel(imageIcon, tokens, this);
				mapPanel.addMouseListener(
					new MouseAdapter() {
						@Override
						public void mouseClicked(MouseEvent e) {
							if (selectedToken != null) {
								tokens.put(selectedToken, getFieldCoordinates(e.getX(), e.getY()));
								mapPanel.updateTokens(tokens);
								selectedToken = null;
							}
						}
					}
				);
				panelImage.add(new JScrollPane(mapPanel), BorderLayout.CENTER);
				setBounds(getX(), getY(), imageIcon.getIconWidth(), imageIcon.getIconHeight());
			} catch (IOException ioe) {
				ioe.printStackTrace();
			}
		}
	}

	private static final int OFFSET_IN_PIXELS = 25;
	private static final int FIELD_SIZE_IN_PIXELS = 50;

	public Coordinates getFieldCoordinates(int x, int y) {
		String fieldX = ((x - OFFSET_IN_PIXELS) / FIELD_SIZE_IN_PIXELS) + ".0";
		String fieldY = ((y - OFFSET_IN_PIXELS) / FIELD_SIZE_IN_PIXELS) + ".0";
		return new Coordinates().setFieldX(new BigDecimal(fieldX)).setFieldY(new BigDecimal(fieldY));
	}

	@Override
	public void tokenHit(Token token, Coordinates coordinates) {
		if (token != null) {
			if (token == mapPanel.getSelectedToken()) {
				mapPanel.setSelectedToken(null);
			} else {
				mapPanel.setSelectedToken(token);
			}
		} else if (mapPanel.getSelectedToken() != null) {
			tokens.put(mapPanel.getSelectedToken(), coordinates);
			mapPanel.updateTokens(tokens);
		}
	}
}
