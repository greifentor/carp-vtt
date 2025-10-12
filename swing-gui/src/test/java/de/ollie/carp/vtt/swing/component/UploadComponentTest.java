package de.ollie.carp.vtt.swing.component;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.spy;
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

@ExtendWith(MockitoExtension.class)
class UploadComponentTest {

	private static final String LABEL = "label";

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
		void throwsAnException_passingANullValue_asButtonLabel() {
			assertThrows(IllegalArgumentException.class, () -> unitUnderTest.build(null));
		}

		@Test
		void addsComponentsCorrectly() {
			// Prepare
			doNothing().when(unitUnderTest).add(panel, BorderLayout.NORTH);
			when(componentFactory.createButton(LABEL, null, null)).thenReturn(buttonUpload);
			when(componentFactory.createPanel()).thenReturn(panel);
			when(componentFactory.createTextField("???", UploadComponent.TEXT_FIELD_FILE_NAME_LENGTH))
				.thenReturn(textFieldFileName);
			// Run
			unitUnderTest.build(LABEL);
			// Check
			InOrder inOrder = inOrder(panel);
			inOrder.verify(panel).add(buttonUpload, BorderLayout.EAST);
			inOrder.verify(panel).add(textFieldFileName, BorderLayout.CENTER);
		}
	}
}
