package de.ollie.carp.vtt.swing;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import de.ollie.carp.vtt.swing.localization.ResourceLoader;
import de.ollie.carp.vtt.swing.localization.ResourceManager;
import java.awt.FlowLayout;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JTextField;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class SwingComponentFactoryTest {

	private static final Integer COLUMNS = 42;
	private static final String LABEL = "label";
	private static final String TEXT = "text";
	private static final String TOOL_TIP_TEXT = "tool-tip-text";

	@Mock
	private JButton button;

	@Mock
	private JTextField textField;

	@Mock
	private Icon icon;

	@Mock
	private ResourceLoader resourceLoader;

	@Mock
	private ResourceManager resourceManager;

	@InjectMocks
	private SwingComponentFactory unitUnderTest;

	@BeforeEach
	void beforeEach() {
		unitUnderTest = spy(unitUnderTest);
	}

	@Nested
	class createBorderLayout {

		@Test
		void returnsANewObject() {
			assertNotNull(unitUnderTest.createBorderLayout());
		}

		@Test
		void returnsANewObject_onEachCall() {
			assertNotSame(unitUnderTest.createBorderLayout(), unitUnderTest.createBorderLayout());
		}

		@Test
		void returnedLayoutManger_hasCorrectHGap() {
			assertEquals(SwingConstants.HGAP, unitUnderTest.createBorderLayout().getHgap());
		}

		@Test
		void returnedLayoutManger_hasCorrectVGap() {
			assertEquals(SwingConstants.VGAP, unitUnderTest.createBorderLayout().getVgap());
		}
	}

	@Nested
	class createButton {

		@Test
		void returnsAnObject() {
			assertNotNull(unitUnderTest.createButton());
		}

		@Test
		void returnsAnNewObject_onEachCall() {
			assertNotSame(unitUnderTest.createButton(), unitUnderTest.createButton());
		}
	}

	@Nested
	class createButton_String_Icon_String {

		private static final String RESOURCE_ID = "resource-id";

		@Test
		void returnsAnObject() {
			assertNotNull(unitUnderTest.createButton(null, null, null));
		}

		@Test
		void returnsAnNewObject_onEachCall() {
			assertNotSame(unitUnderTest.createButton(null, null, null), unitUnderTest.createButton(null, null, null));
		}

		@Test
		void setsNothing_whenAllParametersArePassedAsNull() {
			// Prepare
			when(unitUnderTest.createButton()).thenReturn(button);
			// Run
			unitUnderTest.createButton(null, null, null);
			// Check
			verify(button, never()).setIcon(any());
			verify(button, never()).setText(any());
			verify(button, never()).setToolTipText(any());
		}

		@Test
		void setsIcon_whenPassed() {
			// Prepare
			when(unitUnderTest.createButton()).thenReturn(button);
			// Run
			unitUnderTest.createButton(null, icon, null);
			// Check
			verify(button, times(1)).setIcon(icon);
			verify(button, never()).setText(any());
			verify(button, never()).setToolTipText(any());
		}

		@Test
		void setsLabel_whenPassed() {
			// Prepare
			when(unitUnderTest.createButton()).thenReturn(button);
			when(resourceManager.getResource(RESOURCE_ID)).thenReturn(LABEL);
			// Run
			unitUnderTest.createButton(RESOURCE_ID, null, null);
			// Check
			verify(button, never()).setIcon(any());
			verify(button, times(1)).setText(LABEL);
			verify(button, never()).setToolTipText(any());
		}

		@Test
		void setsToolTipText_whenPassed() {
			// Prepare
			when(unitUnderTest.createButton()).thenReturn(button);
			// Run
			unitUnderTest.createButton(null, null, TOOL_TIP_TEXT);
			// Check
			verify(button, never()).setIcon(any());
			verify(button, never()).setText(any());
			verify(button, times(1)).setToolTipText(TOOL_TIP_TEXT);
		}
	}

	@Nested
	class createFileChooser {

		@Test
		void returnsAnObject() {
			assertNotNull(unitUnderTest.createFileChooser());
		}

		@Test
		void returnsAnNewObject_onEachCall() {
			assertNotSame(unitUnderTest.createFileChooser(), unitUnderTest.createFileChooser());
		}
	}

	@Nested
	class createFileNameProvider_String_Component {

		@Test
		void returnsAnObject() {
			assertNotNull(unitUnderTest.createFileNameProvider());
		}

		@Test
		void returnsAnNewObject_onEachCall() {
			assertNotSame(unitUnderTest.createFileNameProvider(), unitUnderTest.createFileNameProvider());
		}
	}

	@Nested
	class createFlowLayout {

		private static final int ALIGNMENT = FlowLayout.LEADING;

		@Test
		void returnsANewObject() {
			assertNotNull(unitUnderTest.createFlowLayout(ALIGNMENT));
		}

		@Test
		void returnsANewObject_onEachCall() {
			assertNotSame(unitUnderTest.createFlowLayout(ALIGNMENT), unitUnderTest.createFlowLayout(ALIGNMENT));
		}

		@Test
		void returnedLayoutManger_hasCorrectAlignment() {
			assertEquals(ALIGNMENT, unitUnderTest.createFlowLayout(ALIGNMENT).getAlignment());
		}

		@Test
		void returnedLayoutManger_hasCorrectHGap() {
			assertEquals(SwingConstants.HGAP, unitUnderTest.createFlowLayout(ALIGNMENT).getHgap());
		}

		@Test
		void returnedLayoutManger_hasCorrectVGap() {
			assertEquals(SwingConstants.VGAP, unitUnderTest.createFlowLayout(ALIGNMENT).getVgap());
		}
	}

	@Nested
	class createPanel {

		@Test
		void returnsAnObject() {
			assertNotNull(unitUnderTest.createPanel());
		}

		@Test
		void returnsAnNewObject_onEachCall() {
			assertNotSame(unitUnderTest.createPanel(), unitUnderTest.createPanel());
		}
	}

	@Nested
	class createTextField {

		@Test
		void returnsAnObject() {
			assertNotNull(unitUnderTest.createTextField());
		}

		@Test
		void returnsAnNewObject_onEachCall() {
			assertNotSame(unitUnderTest.createTextField(), unitUnderTest.createTextField());
		}
	}

	@Nested
	class createTextField_String_Integer {

		@Test
		void throwsAnException_passingAValueLesserThanOne_asColumns() {
			assertThrows(IllegalArgumentException.class, () -> unitUnderTest.createTextField(null, 0));
		}

		@Test
		void returnsAnObject() {
			assertNotNull(unitUnderTest.createTextField(null, null));
		}

		@Test
		void returnsAnNewObject_onEachCall() {
			assertNotSame(unitUnderTest.createTextField(null, null), unitUnderTest.createTextField(null, null));
		}

		@Test
		void setsNothing_whenAllParametersArePassedAsNull() {
			// Prepare
			when(unitUnderTest.createTextField()).thenReturn(textField);
			// Run
			unitUnderTest.createTextField(null, null);
			// Check
			verify(textField, never()).setText(any());
			verifyNoMoreInteractions(textField);
		}

		@Test
		void setsColumns_whenPassed() {
			// Prepare
			when(unitUnderTest.createTextField()).thenReturn(textField);
			// Run
			unitUnderTest.createTextField(null, COLUMNS);
			// Check
			verify(textField, times(1)).setColumns(COLUMNS);
			verify(textField, never()).setText(any());
		}

		@Test
		void setsText_whenPassed() {
			// Prepare
			when(unitUnderTest.createTextField()).thenReturn(textField);
			// Run
			unitUnderTest.createTextField(TEXT, null);
			// Check
			verify(textField, times(1)).setText(TEXT);
			verifyNoMoreInteractions(textField);
		}
	}

	@Nested
	class postConstruct {

		@Test
		void constructsTheResourceManager() {
			// Run
			unitUnderTest.postConstruct();
			// Check
			verify(resourceLoader, times(1)).loadResources(resourceManager);
		}
	}
}
