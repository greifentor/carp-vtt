package de.ollie.vtt.core.service.port.filesystem;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class FileReadExceptionTest {

	private static final String ABSOLUTE_FILE_NAME = "absolute-file-name";

	@Mock
	private RuntimeException cause;

	private FileReadException unitUnderTest;

	@Nested
	class constructor_String_Throwable {

		@Test
		void setsThePassedAbsoluteFileNameCorrectly() {
			// Run
			unitUnderTest = new FileReadException(ABSOLUTE_FILE_NAME, cause);
			// Check
			assertEquals(ABSOLUTE_FILE_NAME, unitUnderTest.getAbsoluteFileName());
		}

		@Test
		void setsThePassedCauseCorrectly() {
			// Run
			unitUnderTest = new FileReadException(ABSOLUTE_FILE_NAME, cause);
			// Check
			assertSame(cause, unitUnderTest.getCause());
		}
	}
}
