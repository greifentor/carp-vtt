package de.ollie.vtt.core.service.port.filesystem;

public interface BinaryFileAccessPort {
	byte[] readFileContent(String absoluteFileName);
}
