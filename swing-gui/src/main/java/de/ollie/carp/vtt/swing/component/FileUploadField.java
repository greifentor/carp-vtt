package de.ollie.carp.vtt.swing.component;

import de.ollie.carp.vtt.core.service.model.TokenSize;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class FileUploadField extends JPanel {

	private final JComboBox<TokenSize> cmbSize;
	private final JLabel lblInfo;
	private final JButton btnChoose;

	private byte[] content;
	private boolean hasContent;

	public FileUploadField() {
		setLayout(new BorderLayout(5, 0));
		setPreferredSize(new Dimension(450, 28)); // Höhe wie JTextField

		// ComboBox links
		cmbSize = new JComboBox<>(TokenSize.values());

		// Info in der Mitte
		lblInfo = new JLabel("Keine Datei ausgewählt");

		// Button rechts
		btnChoose = new JButton("Datei wählen");

		JPanel leftPanel = new JPanel(new BorderLayout());
		leftPanel.add(cmbSize, BorderLayout.WEST);
		leftPanel.add(lblInfo, BorderLayout.CENTER);

		add(leftPanel, BorderLayout.CENTER);
		add(btnChoose, BorderLayout.EAST);

		btnChoose.addActionListener(e -> chooseFile());
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
			content = fis.readAllBytes();
			hasContent = true;

			lblInfo.setText(file.getName() + " (" + content.length + " Bytes)");
		} catch (IOException ex) {
			hasContent = false;
			content = null;
			lblInfo.setText("Fehler beim Laden");
			ex.printStackTrace();
		}
	}

	public byte[] getContent() {
		return content;
	}

	public boolean hasContent() {
		return hasContent;
	}

	public TokenSize getTokenSize() {
		return (TokenSize) cmbSize.getSelectedItem();
	}
}
