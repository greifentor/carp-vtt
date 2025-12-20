package de.ollie.carp.vtt.swing;

import static de.ollie.carp.vtt.swing.SwingConstants.HGAP;
import static de.ollie.carp.vtt.swing.SwingConstants.VGAP;

import de.ollie.carp.vtt.core.service.MapService;
import de.ollie.carp.vtt.core.service.model.Map;
import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MapJInternalFrame extends JInternalFrame implements ActionListener, ItemListener {

	private final JDesktopPane desktopPane;
	private final MapService mapService;

	private JComboBox<Map> comboBoxMaps;
	private JPanel panelImage;

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
		comboBoxMaps.addItemListener(this);
		panelImage = new JPanel(new BorderLayout(HGAP, VGAP));
		p.add(comboBoxMaps, BorderLayout.NORTH);
		p.add(panelImage, BorderLayout.CENTER);
		return p;
	}

	private JComboBox<Map> createMapsComboBox() {
		return new JComboBox<>(mapService.findAll().toArray(new Map[0]));
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("START");
		if (e.getSource() == comboBoxMaps) {
			panelImage.removeAll();
			try {
				Image image = ImageIO.read(new ByteArrayInputStream((((Map) comboBoxMaps.getSelectedItem()).getImage())));
				ImageIcon imageIcon = new ImageIcon(image);
				panelImage.add(new JScrollPane(new JLabel(imageIcon)), BorderLayout.CENTER);
				setBounds(getX(), getY(), imageIcon.getIconWidth(), imageIcon.getIconHeight());
				panelImage.revalidate();
				panelImage.repaint();
				System.out.println("FINISHED");
			} catch (IOException ioe) {
				ioe.printStackTrace();
			}
		}
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		System.out.println("START");
		if (e.getSource() == comboBoxMaps) {
			panelImage.removeAll();
			try {
				Image image = ImageIO.read(new ByteArrayInputStream((((Map) comboBoxMaps.getSelectedItem()).getImage())));
				ImageIcon imageIcon = new ImageIcon(image);
				panelImage.add(new JScrollPane(new JLabel(imageIcon)), BorderLayout.CENTER);
				setBounds(getX(), getY(), imageIcon.getIconWidth(), imageIcon.getIconHeight());
				panelImage.revalidate();
				panelImage.repaint();
				System.out.println("FINISHED");
			} catch (IOException ioe) {
				ioe.printStackTrace();
			}
		}
	}
}
