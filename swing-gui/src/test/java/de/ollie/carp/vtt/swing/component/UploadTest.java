package de.ollie.carp.vtt.swing.component;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import de.ollie.carp.vtt.core.service.port.filesystem.BinaryFileAccessPort;
import de.ollie.carp.vtt.swing.SwingComponentFactory;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

@ExtendWith(MockitoExtension.class)
class UploadTest {

	private static final byte[] CONTENT = new byte[] { 1, 2, 3, 4, 5 };
	private static final String LABEL = "label";
	private static final String PATH = "path";

	@Mock
	private BinaryFileAccessPort binaryFileAccessPort;

	@Mock
	private JButton buttonUpload;

	@Mock
	private JTextField textFieldFileName;

	@Mock
	private JPanel panel;

	@Mock
	private SwingComponentFactory componentFactory;

	private Upload unitUnderTest;

	@BeforeEach
	void beforeEach() {
		unitUnderTest = spy(new Upload(binaryFileAccessPort, componentFactory, CONTENT));
	}

	@Nested
	class constructor {

		@Test
		void throwsAnException_passingANullValue_asBinaryFileAccessPort() {
			assertThrows(IllegalArgumentException.class, () -> new Upload(null, componentFactory, CONTENT));
		}

		@Test
		void throwsAnException_passingANullValue_asSwingComponentFactory() {
			assertThrows(IllegalArgumentException.class, () -> new Upload(binaryFileAccessPort, null, CONTENT));
		}

		@Test
		void setsUploadContentCorrectly() {
			assertSame(CONTENT, new Upload(binaryFileAccessPort, componentFactory, CONTENT).getValue());
		}
	}

	@Nested
	class build_String {

		@Test
		void addsComponentsCorrectly() {
			// Prepare
			doNothing().when(unitUnderTest).add(panel, BorderLayout.NORTH);
			when(componentFactory.createButton(Upload.RES_ID_BROWSE_BUTTON, null, null)).thenReturn(buttonUpload);
			when(componentFactory.createPanel()).thenReturn(panel);
			when(componentFactory.createTextField("???", Upload.TEXT_FIELD_FILE_NAME_LENGTH)).thenReturn(textFieldFileName);
			// Run
			unitUnderTest.build();
			// Check
			InOrder inOrder = inOrder(panel);
			inOrder.verify(panel).add(buttonUpload, BorderLayout.EAST);
			inOrder.verify(panel).add(textFieldFileName, BorderLayout.CENTER);
		}
	}

	@Nested
	class getValue {

		@Test
		void returnsTheUploadContent() {
			// Prepare
			ReflectionTestUtils.setField(unitUnderTest, "uploadContent", CONTENT);
			// Run & Check
			assertSame(CONTENT, unitUnderTest.getValue());
		}
	}

	@Nested
	class update_byteArr_String {

		@Test
		void happyRun() {
			// Prepare
			ReflectionTestUtils.setField(unitUnderTest, "textFieldFileName", textFieldFileName);
			// Run
			unitUnderTest.update(CONTENT, PATH);
			// Check
			verify(textFieldFileName, times(1)).setText(PATH);
			assertEquals(CONTENT, ReflectionTestUtils.getField(unitUnderTest, "uploadContent"));
		}
	}
}
