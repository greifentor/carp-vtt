package de.ollie.carp.vtt.swing;

import static de.ollie.carp.vtt.swing.SwingConstants.HGAP;
import static de.ollie.carp.vtt.swing.SwingConstants.VGAP;

import de.ollie.carp.vtt.core.service.MapService;
import de.ollie.carp.vtt.core.service.TokenService;
import de.ollie.carp.vtt.core.service.model.Map;
import de.ollie.carp.vtt.core.service.model.Token;
import de.ollie.carp.vtt.swing.component.TokenSelectionDialog;
import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@RequiredArgsConstructor
public class MapJInternalFrame extends JInternalFrame implements ActionListener, ItemListener, MouseListener {

	private final JDesktopPane desktopPane;
	private final transient MapService mapService;
	private final transient TokenService tokenService;

	private JButton buttonAddIcon = new JButton("+");
	private JComboBox<Map> comboBoxMaps;
	private JLabel labelImage;
	private JPanel panelImage;

	public MapJInternalFrame prepare() {
		desktopPane.add(this);
		setClosable(true);
		setIconifiable(true);
		setMaximizable(true);
		setResizable(true);
		setBounds(50, 50, 640, 480);
		setContentPane(createMainPanel());
		labelImage = new JLabel();
		pack();
		return this;
	}

	private JPanel createMainPanel() {
		JPanel p = new JPanel(new BorderLayout(HGAP, VGAP));
		comboBoxMaps = createMapsComboBox();
		comboBoxMaps.setRenderer(new MapListCellRenderer());
		comboBoxMaps.addActionListener(this);
		comboBoxMaps.addItemListener(this);
		panelImage = new JPanel(new BorderLayout(HGAP, VGAP));
		panelImage.addMouseListener(this);
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
			Token token = new TokenSelectionDialog(null, tokenService.findAll()).getSelectedToken();
			if (token != null) {
				System.out.println(token.getName());
			}
		} else if (e.getSource() == comboBoxMaps) {
			panelImage.removeAll();
			labelImage.removeMouseListener(this);
			try {
				Image image = ImageIO.read(new ByteArrayInputStream((((Map) comboBoxMaps.getSelectedItem()).getImage())));
				ImageIcon imageIcon = new ImageIcon(image);
				labelImage.setIcon(imageIcon);
				labelImage.addMouseListener(this);
				panelImage.add(new JScrollPane(labelImage), BorderLayout.CENTER);
				setBounds(getX(), getY(), imageIcon.getIconWidth(), imageIcon.getIconHeight());
				panelImage.revalidate();
				panelImage.repaint();
			} catch (IOException ioe) {
				ioe.printStackTrace();
			}
		}
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		if (e.getSource() == comboBoxMaps) {
			panelImage.removeAll();
			labelImage.removeMouseListener(this);
			try {
				Image image = ImageIO.read(new ByteArrayInputStream((((Map) comboBoxMaps.getSelectedItem()).getImage())));
				ImageIcon imageIcon = new ImageIcon(image);
				labelImage.setIcon(imageIcon);
				labelImage.addMouseListener(this);
				panelImage.add(new JScrollPane(labelImage), BorderLayout.CENTER);
				setBounds(getX(), getY(), imageIcon.getIconWidth(), imageIcon.getIconHeight());
				panelImage.revalidate();
				panelImage.repaint();
			} catch (IOException ioe) {
				ioe.printStackTrace();
			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		System.out.println(getFieldCoordinates(e.getX(), e.getY()));
	}

	@AllArgsConstructor
	@Getter
	@ToString
	private static class Coordinates {

		private BigDecimal fieldX;
		private BigDecimal fieldY;
	}

	private static final int OFFSET_IN_PIXELS = 25;
	private static final int FIELD_SIZE_IN_PIXELS = 50;

	public Coordinates getFieldCoordinates(int x, int y) {
		String fieldX = ((x - OFFSET_IN_PIXELS) / FIELD_SIZE_IN_PIXELS) + ".0";
		String fieldY = ((y - OFFSET_IN_PIXELS) / FIELD_SIZE_IN_PIXELS) + ".0";
		return new Coordinates(new BigDecimal(fieldX), new BigDecimal(fieldY));
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}
}
