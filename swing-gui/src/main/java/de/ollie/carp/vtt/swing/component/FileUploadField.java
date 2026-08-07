package de.ollie.carp.vtt.swing.component;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class FileUploadField extends JPanel {

	private final JLabel labelInfo;
	private final JButton buttonChoose;

	private byte[] content;
	private boolean hasContent;

	public FileUploadField() {
		setLayout(new BorderLayout(5, 0));
		setPreferredSize(new Dimension(450, 28));
		labelInfo = new JLabel("Keine Datei ausgewählt");
		buttonChoose = new JButton("Datei wählen");
		JPanel leftPanel = new JPanel(new BorderLayout());
		leftPanel.add(labelInfo, BorderLayout.CENTER);
		add(leftPanel, BorderLayout.CENTER);
		add(buttonChoose, BorderLayout.EAST);
		buttonChoose.addActionListener(e -> chooseFile());
	}

	private void chooseFile() {
		JFileChooser chooser = new JFileChooser();
		int result = chooser.showOpenDialog(this);
		if (result == JFileChooser.APPROVE_OPTION) {
			File file = chooser.getSelectedFile();
			loadFile(file);
		}
	}

	private void loadFile(File file) {
		try (FileInputStream fis = new FileInputStream(file)) {
			hasContent = true;
			content = fis.readAllBytes();
			labelInfo.setText(file.getName() + " (" + content.length + " Bytes)");
		} catch (IOException ex) {
			hasContent = false;
			content = null;
			labelInfo.setText("Fehler beim Laden");
			ex.printStackTrace();
		}
	}

	public byte[] getContent() {
		return content;
	}

	public boolean hasContent() {
		return hasContent;
	}
}
