package de.ollie.carp.vtt.swing.component;

import static de.ollie.baselib.util.Check.ensure;
import static de.ollie.carp.vtt.swing.SwingConstants.HGAP;
import static de.ollie.carp.vtt.swing.SwingConstants.VGAP;

import de.ollie.carp.vtt.swing.SwingComponentFactory;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class UploadComponent extends JPanel {

	static final int TEXT_FIELD_FILE_NAME_LENGTH = 40;

	private byte[] uploadContent;

	private JButton buttonUpload;
	private JTextField textFieldFileName;

	public UploadComponent() {
		setLayout(new BorderLayout(HGAP, VGAP));
	}

	public UploadComponent build(SwingComponentFactory componentFactory, String buttonLabel) {
		ensure(buttonLabel != null, "button label cannot be null!");
		ensure(componentFactory != null, "component factory cannot be null!");
		buttonUpload = componentFactory.createButton(buttonLabel, null, null);
		textFieldFileName = componentFactory.createTextField("", TEXT_FIELD_FILE_NAME_LENGTH);
		JPanel p = componentFactory.createPanel();
		p.setLayout(new BorderLayout(HGAP, VGAP));
		p.add(buttonUpload, BorderLayout.EAST);
		p.add(textFieldFileName, BorderLayout.CENTER);
		add(p, BorderLayout.NORTH);
		return this;
	}
}
