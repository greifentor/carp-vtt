package de.ollie.carp.vtt.service.fs.impl;

import jakarta.inject.Named;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Named
class BinaryFileReader {

	byte[] read(String absoluteFileName) throws IOException {
		return Files.readAllBytes(Path.of(absoluteFileName));
	}
}
