package de.ollie.carp.vtt.swing.component;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import de.ollie.carp.vtt.swing.SwingComponentFactory;
import java.awt.Component;
import java.io.File;
import javax.swing.JFileChooser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.io.TempDir;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class FileNameProviderTest {

	private static final String ABSOLUTE_FILE_NAME = "/absolute/file/name";
	private static final String CURRENT_DIR = "current-dir";

	@TempDir
	private File tempDir;

	@Mock
	private Component parent;

	@Mock
	private SwingComponentFactory componentactory;

	private FileNameProvider unitUnderTest;

	@BeforeEach
	void beforeEach() {
		unitUnderTest = spy(new FileNameProvider(componentactory));
	}

	@Nested
	class getFileNameByDialog_File_SwingComponentFactory {

		@Test
		void setsTheCurrentDirectoryCorrectly() {
			// Prepare
			File file = mock(File.class);
			JFileChooser fileChooser = mock(JFileChooser.class);
			when(unitUnderTest.createFile(CURRENT_DIR)).thenReturn(file);
			when(componentactory.createFileChooser()).thenReturn(fileChooser);
			when(fileChooser.showOpenDialog(parent)).thenReturn(JFileChooser.APPROVE_OPTION + 1);
			// Run
			unitUnderTest.getFileNameByDialog(CURRENT_DIR, parent);
			// Check
			verify(fileChooser, times(1)).setCurrentDirectory(file);
		}

		@Test
		void returnsAnEmptyOptional_whenNoFileHasBeenSelected() {
			// Prepare
			JFileChooser fileChooser = mock(JFileChooser.class);
			when(componentactory.createFileChooser()).thenReturn(fileChooser);
			when(fileChooser.showOpenDialog(parent)).thenReturn(JFileChooser.APPROVE_OPTION + 1);
			// Run & Check
			assertTrue(unitUnderTest.getFileNameByDialog(CURRENT_DIR, parent).isEmpty());
		}

		@Test
		void returnsAOptionalWithTheAbsoluteFileName_whenAFileHasBeenSelected() {
			// Prepare
			JFileChooser fileChooser = mock(JFileChooser.class);
			File selectedFile = mock(File.class);
			when(componentactory.createFileChooser()).thenReturn(fileChooser);
			when(fileChooser.getSelectedFile()).thenReturn(selectedFile);
			when(fileChooser.showOpenDialog(parent)).thenReturn(JFileChooser.APPROVE_OPTION);
			when(selectedFile.getAbsolutePath()).thenReturn(tempDir + ABSOLUTE_FILE_NAME);
			// Run & Check
			assertEquals(tempDir + ABSOLUTE_FILE_NAME, unitUnderTest.getFileNameByDialog(CURRENT_DIR, parent).get());
		}
	}

	@Nested
	class createFile_String {

		@Test
		void returnsNull_passingANullValue_asFileNameAbsolutePath() {
			assertNull(unitUnderTest.createFile(null));
		}

		@Test
		void returnsAFileWithCorrectName_passingAString_asFileNameAbsolutePath() {
			assertEquals(
				(tempDir + ABSOLUTE_FILE_NAME).replace("\\", "/"),
				unitUnderTest.createFile(tempDir + ABSOLUTE_FILE_NAME).getAbsolutePath().replace("\\", "/")
			);
		}
	}
}
