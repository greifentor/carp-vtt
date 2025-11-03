package de.ollie.carp.vtt.swing.component;

import static de.ollie.baselib.util.Check.ensure;
import static de.ollie.carp.vtt.swing.SwingConstants.HGAP;
import static de.ollie.carp.vtt.swing.SwingConstants.VGAP;

import de.ollie.carp.vtt.core.service.port.filesystem.BinaryFileAccessPort;
import de.ollie.carp.vtt.swing.SwingComponentFactory;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Upload extends JPanel {

	static final String RES_ID_BROWSE_BUTTON = "UploadComponent.button.browse.label";
	static final int TEXT_FIELD_FILE_NAME_LENGTH = 40;

	private final BinaryFileAccessPort binaryFileAccessPort;
	private final SwingComponentFactory componentFactory;

	private byte[] uploadContent;

	private JButton buttonUpload;
	private JTextField textFieldFileName;

	public Upload(
		BinaryFileAccessPort binaryFileAccessPort,
		SwingComponentFactory componentFactory,
		byte[] uploadContent
	) {
		ensure(binaryFileAccessPort != null, "binary file access port cannot be null!");
		ensure(componentFactory != null, "component factory cannot be null!");
		this.binaryFileAccessPort = binaryFileAccessPort;
		this.componentFactory = componentFactory;
		this.uploadContent = uploadContent;
		setLayout(new BorderLayout(HGAP, VGAP));
	}

	public Upload build() {
		buttonUpload = componentFactory.createButton(RES_ID_BROWSE_BUTTON, null, null);
		buttonUpload.addActionListener(e -> upload());
		textFieldFileName = componentFactory.createTextField("???", TEXT_FIELD_FILE_NAME_LENGTH);
		JPanel p = componentFactory.createPanel();
		p.setLayout(new BorderLayout(HGAP, VGAP));
		p.add(buttonUpload, BorderLayout.EAST);
		p.add(textFieldFileName, BorderLayout.CENTER);
		add(p, BorderLayout.NORTH);
		return this;
	}

	private void upload() {
		componentFactory
			.createFileNameProvider()
			.getFileNameByDialog(null, this)
			.ifPresent(path -> update(binaryFileAccessPort.readFileContent(path), path));
	}

	void update(byte[] content, String path) {
		uploadContent = content;
		textFieldFileName.setText(path);
	}

	public byte[] getValue() {
		return uploadContent;
	}
}
