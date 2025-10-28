package de.ollie.carp.vtt.swing.component;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import de.ollie.carp.vtt.swing.SwingComponentFactory;
import de.ollie.carp.vtt.swing.component.EditorButtonPanel.Observer;
import java.awt.BorderLayout;
import java.awt.LayoutManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

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
	class prepare {

		@Test
		void returns_itself() {
			assertSame(unitUnderTest, unitUnderTest.prepare());
		}

		@Test
		void setTheCorrectLayout() {
			// Prepare
			BorderLayout layout = mock(BorderLayout.class);
			doNothing().when(unitUnderTest).setLayout(any(LayoutManager.class));
			when(swingComponentFactory.createBorderLayout()).thenReturn(layout);
			// Run
			unitUnderTest.prepare();
			// Check
			verify(unitUnderTest, times(1)).setLayout(layout);
		}
	}
}
