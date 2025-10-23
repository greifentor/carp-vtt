package de.ollie.carp.vtt.service.fs.impl;

import static de.ollie.baselib.util.Check.ensure;

import de.ollie.vtt.core.service.port.filesystem.BinaryFileAccessPort;
import de.ollie.vtt.core.service.port.filesystem.FileReadException;
import jakarta.inject.Named;
import lombok.RequiredArgsConstructor;

@Named
@RequiredArgsConstructor
class BinaryFileAccessAdapter implements BinaryFileAccessPort {

	private final BinaryFileReader binaryFileReader;

	@Override
	public byte[] readFileContent(String absoluteFileName) throws FileReadException {
		ensure(absoluteFileName != null, "absolute file name cannot be null!");
		try {
			return binaryFileReader.read(absoluteFileName);
		} catch (Exception e) {
			throw new FileReadException(absoluteFileName, e);
		}
	}
}
