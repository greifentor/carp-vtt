package de.ollie.carp.vtt.swing.component;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import de.ollie.carp.vtt.swing.SwingComponentFactory;
import de.ollie.vtt.core.service.port.filesystem.BinaryFileAccessPort;
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
class UploadComponentTest {

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

	private UploadComponent unitUnderTest;

	@BeforeEach
	void beforeEach() {
		unitUnderTest = spy(new UploadComponent(binaryFileAccessPort, componentFactory));
	}

	@Nested
	class constructor {

		@Test
		void throwsAnException_passingANullValue_asBinaryFileAccessPort() {
			assertThrows(IllegalArgumentException.class, () -> new UploadComponent(null, componentFactory));
		}

		@Test
		void throwsAnException_passingANullValue_asSwingComponentFactory() {
			assertThrows(IllegalArgumentException.class, () -> new UploadComponent(binaryFileAccessPort, null));
		}
	}

	@Nested
	class build_String {

		@Test
		void addsComponentsCorrectly() {
			// Prepare
			doNothing().when(unitUnderTest).add(panel, BorderLayout.NORTH);
			when(componentFactory.createButton(UploadComponent.RES_ID_BROWSE_BUTTON, null, null)).thenReturn(buttonUpload);
			when(componentFactory.createPanel()).thenReturn(panel);
			when(componentFactory.createTextField("???", UploadComponent.TEXT_FIELD_FILE_NAME_LENGTH))
				.thenReturn(textFieldFileName);
			// Run
			unitUnderTest.build();
			// Check
			InOrder inOrder = inOrder(panel);
			inOrder.verify(panel).add(buttonUpload, BorderLayout.EAST);
			inOrder.verify(panel).add(textFieldFileName, BorderLayout.CENTER);
		}
	}

	@Nested
	class update_byteArr_String {

		@Test
		void happyRun() {
			// Prepare
			byte[] content = new byte[] { 1, 2, 3, 4, 5 };
			ReflectionTestUtils.setField(unitUnderTest, "textFieldFileName", textFieldFileName);
			// Run
			unitUnderTest.update(content, PATH);
			// Check
			verify(textFieldFileName, times(1)).setText(PATH);
			assertEquals(content, ReflectionTestUtils.getField(unitUnderTest, "uploadContent"));
		}
	}
}
