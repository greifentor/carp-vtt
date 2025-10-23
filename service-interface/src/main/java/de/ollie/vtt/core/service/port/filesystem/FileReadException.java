package de.ollie.vtt.core.service.port.filesystem;

import lombok.Getter;

@Getter
public class FileReadException extends RuntimeException {

	private final String absoluteFileName;

	public FileReadException(String absoluteFileName, Throwable throwable) {
		super(throwable);
		this.absoluteFileName = absoluteFileName;
	}
}
