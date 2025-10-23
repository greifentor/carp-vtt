package de.ollie.carp.vtt.swing;

import de.ollie.carp.vtt.swing.component.UploadComponent;
import de.ollie.vtt.core.service.port.filesystem.BinaryFileAccessPort;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class TokenEditJInternalFrame extends JInternalFrame {

	private final BinaryFileAccessPort binaryFileAccessPort;
	private final SwingComponentFactory swingComponentFactory;
	private final JDesktopPane desktopPane;

	public TokenEditJInternalFrame(
		BinaryFileAccessPort binaryFileAccessPort,
		SwingComponentFactory swingComponentFactory,
		JDesktopPane desktopPane
	) {
		super("title", true, true, true, true);
		this.binaryFileAccessPort = binaryFileAccessPort;
		this.desktopPane = desktopPane;
		this.swingComponentFactory = swingComponentFactory;
	}

	TokenEditJInternalFrame prepare() {
		JPanel panel = new JPanel(new BorderLayout(SwingConstants.HGAP, SwingConstants.VGAP));
		panel.setBorder(
			new EmptyBorder(SwingConstants.VGAP, SwingConstants.HGAP, SwingConstants.VGAP, SwingConstants.HGAP)
		);
		JPanel panelLabels = new JPanel(new GridLayout(2, 1, SwingConstants.HGAP, SwingConstants.VGAP));
		panelLabels.add(new JLabel("Name:"));
		panelLabels.add(new JLabel("Content:"));
		JPanel panelFields = new JPanel(new GridLayout(2, 1, SwingConstants.HGAP, SwingConstants.VGAP));
		panelFields.add(new JTextField(40));
		panelFields.add(new UploadComponent(binaryFileAccessPort, swingComponentFactory).build("DOOF"));
		panel.add(panelLabels, BorderLayout.WEST);
		panel.add(panelFields, BorderLayout.CENTER);
		JPanel mainLayout = new JPanel(new BorderLayout(SwingConstants.HGAP, SwingConstants.VGAP));
		mainLayout.add(panel, BorderLayout.NORTH);
		setContentPane(mainLayout);
		try {
			setSelected(true);
		} catch (java.beans.PropertyVetoException e) {
			e.printStackTrace();
		}
		setBounds(50, 50, 640, 480);
		pack();
		desktopPane.add(this);
		return this;
	}
}
