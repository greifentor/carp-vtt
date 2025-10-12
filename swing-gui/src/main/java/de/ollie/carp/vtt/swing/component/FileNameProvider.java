package de.ollie.carp.vtt.swing.component;

import de.ollie.carp.vtt.swing.SwingComponentFactory;
import java.awt.Component;
import java.io.File;
import java.util.Optional;
import javax.swing.JFileChooser;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class FileNameProvider {

	@NonNull
	private final SwingComponentFactory componentFactory;

	public Optional<String> getFileNameByDialog(String fileNameAbsolutePath, Component parent) {
		JFileChooser fileChooser = componentFactory.createFileChooser();
		fileChooser.setCurrentDirectory(createFile(fileNameAbsolutePath));
		return (fileChooser.showOpenDialog(parent) == JFileChooser.APPROVE_OPTION)
			? Optional.of(fileChooser.getSelectedFile().getAbsolutePath())
			: Optional.empty();
	}

	File createFile(String fileNameAbsolutePath) {
		return fileNameAbsolutePath != null ? new File(fileNameAbsolutePath) : null;
	}
}
