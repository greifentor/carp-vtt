package de.ollie.carp.vtt.swing.component;

import static de.ollie.carp.vtt.swing.SwingConstants.HGAP;
import static de.ollie.carp.vtt.swing.SwingConstants.VGAP;

import de.ollie.carp.vtt.swing.SwingComponentFactory;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class UploadComponent extends JPanel {

	private byte[] uploadContent;

	private JButton buttonUpload;
	private JTextField textFieldFileName;

	public UploadComponent(SwingComponentFactory componentFactory, String buttonLabel) {
		setLayout(new BorderLayout(HGAP, VGAP));
	}
}
