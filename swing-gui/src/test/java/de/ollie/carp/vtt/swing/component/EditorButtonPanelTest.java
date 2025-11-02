package de.ollie.carp.vtt.swing.component;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import de.ollie.carp.vtt.swing.SwingComponentFactory;
import de.ollie.carp.vtt.swing.component.EditorButtonPanel.ButtonType;
import de.ollie.carp.vtt.swing.component.EditorButtonPanel.Observer;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.LayoutManager;
import javax.swing.JButton;
import javax.swing.JPanel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

@ExtendWith(MockitoExtension.class)
class EditorButtonPanelTest {

	@Mock
	private Observer observer;

	@Mock
	private SwingComponentFactory swingComponentFactory;

	@InjectMocks
	private EditorButtonPanel unitUnderTest;

	@BeforeEach
	void beforeEach() {
		unitUnderTest = spy(unitUnderTest);
	}

	@Nested
	class createLeftButtonPanel {

		private static final String DELETE_LABEL = "Delete";

		@Mock
		private FlowLayout layout;

		private JButton button = new JButton();

		@Mock
		private JPanel panel;

		@Test
		void returnsTheCorrectPanel() {
			// Prepare
			trainMocks();
			// Run
			JPanel returned = unitUnderTest.createLeftButtonPanel();
			// Check
			assertEquals(panel, returned);
		}

		private void trainMocks() {
			when(swingComponentFactory.createButton("EditorPanel.button.delete.label", null, null)).thenReturn(button);
			when(swingComponentFactory.createFlowLayout(FlowLayout.LEFT)).thenReturn(layout);
			when(swingComponentFactory.createPanel()).thenReturn(panel);
		}

		@Test
		void addTheDeleteButton() {
			// Prepare
			trainMocks();
			// Run
			unitUnderTest.createLeftButtonPanel();
			// Check
			verify(panel, times(1)).add(button);
		}

		@Test
		void setsTheCorrectLayoutManager() {
			// Prepare
			trainMocks();
			// Run
			unitUnderTest.createLeftButtonPanel();
			// Check
			verify(panel, times(1)).setLayout(layout);
		}

		@Test
		void createsTheDeleteButton() {
			// Prepare
			trainMocks();
			// Run
			unitUnderTest.createLeftButtonPanel();
			// Check
			assertSame(button, ReflectionTestUtils.getField(unitUnderTest, "buttonDelete"));
		}

		@Test
		void callsTheObserver_withCorrectButtonType_whenButtonClicked() {
			// Prepare
			trainMocks();
			unitUnderTest.createLeftButtonPanel();
			// Run
			button.doClick();
			// Check
			verify(observer, times(1)).buttonPressed(ButtonType.DELETE);
		}

		@Test
		void doesNotCallsTheObserver_whenButtonClicked_butObserverNotSet() {
			// Prepare
			trainMocks();
			unitUnderTest.createLeftButtonPanel();
			ReflectionTestUtils.setField(unitUnderTest, "observer", null);
			// Run
			button.doClick();
			// Check
			verify(observer, never()).buttonPressed(any(ButtonType.class));
		}
	}

	@Nested
	class createRightButtonPanel {

		private static final String CANCEL_LABEL = "Cancel";
		private static final String OK_LABEL = "Ok";

		@Mock
		private FlowLayout layout;

		private JButton buttonCancel = new JButton();

		private JButton buttonOk = new JButton();

		@Mock
		private JPanel panel;

		@Test
		void returnsTheCorrectPanel() {
			// Prepare
			trainMocks();
			// Run
			JPanel returned = unitUnderTest.createRightButtonPanel();
			// Check
			assertEquals(panel, returned);
		}

		private void trainMocks() {
			when(swingComponentFactory.createButton("EditorPanel.button.cancel.label", null, null)).thenReturn(buttonCancel);
			when(swingComponentFactory.createButton("EditorPanel.button.ok.label", null, null)).thenReturn(buttonOk);
			when(swingComponentFactory.createFlowLayout(FlowLayout.RIGHT)).thenReturn(layout);
			when(swingComponentFactory.createPanel()).thenReturn(panel);
		}

		@Test
		void addsTheButtonInCorrectOrder() {
			// Prepare
			trainMocks();
			// Run
			unitUnderTest.createRightButtonPanel();
			// Check
			InOrder inOrder = inOrder(panel);
			inOrder.verify(panel, times(1)).add(buttonOk);
			inOrder.verify(panel, times(1)).add(buttonCancel);
		}

		@Test
		void setsTheCorrectLayoutManager() {
			// Prepare
			trainMocks();
			// Run
			unitUnderTest.createRightButtonPanel();
			// Check
			verify(panel, times(1)).setLayout(layout);
		}

		@Test
		void createsTheCancelButton() {
			// Prepare
			trainMocks();
			// Run
			unitUnderTest.createRightButtonPanel();
			// Check
			assertSame(buttonCancel, ReflectionTestUtils.getField(unitUnderTest, "buttonCancel"));
		}

		@Test
		void createsTheOkButton() {
			// Prepare
			trainMocks();
			// Run
			unitUnderTest.createRightButtonPanel();
			// Check
			assertSame(buttonOk, ReflectionTestUtils.getField(unitUnderTest, "buttonOk"));
		}

		@Test
		void callsTheObserver_withCorrectButtonTypeCancel_whenButtonClicked() {
			// Prepare
			trainMocks();
			unitUnderTest.createRightButtonPanel();
			// Run
			buttonCancel.doClick();
			// Check
			verify(observer, times(1)).buttonPressed(ButtonType.CANCEL);
		}

		@Test
		void doesNotCallsTheObserver_whenButtonClickedCancel_butObserverNotSet() {
			// Prepare
			trainMocks();
			unitUnderTest.createRightButtonPanel();
			ReflectionTestUtils.setField(unitUnderTest, "observer", null);
			// Run
			buttonCancel.doClick();
			// Check
			verify(observer, never()).buttonPressed(any(ButtonType.class));
		}

		@Test
		void callsTheObserver_withCorrectButtonTypeOk_whenButtonClicked() {
			// Prepare
			trainMocks();
			unitUnderTest.createRightButtonPanel();
			// Run
			buttonOk.doClick();
			// Check
			verify(observer, times(1)).buttonPressed(ButtonType.OK);
		}

		@Test
		void doesNotCallsTheObserver_whenButtonClickedOk_butObserverNotSet() {
			// Prepare
			trainMocks();
			unitUnderTest.createRightButtonPanel();
			ReflectionTestUtils.setField(unitUnderTest, "observer", null);
			// Run
			buttonOk.doClick();
			// Check
			verify(observer, never()).buttonPressed(any(ButtonType.class));
		}
	}

	@Nested
	class prepare {

		@Mock
		private BorderLayout layout;

		@Mock
		private JPanel panelLeft;

		@Mock
		private JPanel panelRight;

		@Test
		void returns_itself() {
			// Prepare
			trainMocks();
			// Run
			assertSame(unitUnderTest, unitUnderTest.prepare());
		}

		@Test
		void setTheCorrectLayout() {
			// Prepare
			trainMocks();
			// Run
			unitUnderTest.prepare();
			// Check
			verify(unitUnderTest, times(1)).setLayout(layout);
		}

		private void trainMocks() {
			doNothing().when(unitUnderTest).add(any(Component.class), anyString());
			doNothing().when(unitUnderTest).setLayout(any(LayoutManager.class));
			doReturn(panelLeft).when(unitUnderTest).createLeftButtonPanel();
			doReturn(panelRight).when(unitUnderTest).createRightButtonPanel();
			when(swingComponentFactory.createBorderLayout()).thenReturn(layout);
		}

		@Test
		void addsLeftButtonPanel() {
			// Prepare
			trainMocks();
			// Run
			unitUnderTest.prepare();
			// Check
			verify(unitUnderTest, times(1)).add(panelLeft, BorderLayout.WEST);
		}

		@Test
		void addsRightButtonPanel() {
			// Prepare
			trainMocks();
			// Run
			unitUnderTest.prepare();
			// Check
			verify(unitUnderTest, times(1)).add(panelRight, BorderLayout.EAST);
		}
	}
}
