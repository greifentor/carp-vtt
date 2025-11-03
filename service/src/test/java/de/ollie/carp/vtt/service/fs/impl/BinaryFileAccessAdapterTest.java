package de.ollie.carp.vtt.service.fs.impl;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import de.ollie.carp.vtt.core.service.port.filesystem.FileReadException;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.io.TempDir;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

@ExtendWith(MockitoExtension.class)
class BinaryFileAccessAdapterTest {

	private static final byte[] BINARY_CONTENT = new byte[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 42 };
	private static final String FILE_NAME = "file-name.binary";

	@Mock
	private BinaryFileReader binaryFileReader;

	@TempDir
	private File tempDir;

	@InjectMocks
	private BinaryFileAccessAdapter unitUnderTest;

	@Nested
	class readFileContent_String {

		@Test
		void throwsAnException_passingANullValue_asAbsoluteFileName() {
			assertThrows(IllegalArgumentException.class, () -> unitUnderTest.readFileContent(null));
		}

		@Test
		void readsTheFileCorrectly() throws Exception {
			// Prepare
			String fileName = tempDir.getAbsolutePath() + "/" + FILE_NAME;
			Files.write(Path.of(fileName), BINARY_CONTENT, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
			ReflectionTestUtils.setField(unitUnderTest, "binaryFileReader", new BinaryFileReader());
			// Run
			byte[] returned = unitUnderTest.readFileContent(fileName);
			// Check
			assertArrayEquals(BINARY_CONTENT, returned);
		}

		@Test
		void throwsTheCorrectException_whenSomethingGoesWrongWhileRadingTheFile() throws Exception {
			// Prepare
			IOException ioException = mock(IOException.class);
			when(binaryFileReader.read(FILE_NAME)).thenThrow(ioException);
			// Run & Check
			assertThrows(FileReadException.class, () -> unitUnderTest.readFileContent(FILE_NAME));
		}
	}
}
