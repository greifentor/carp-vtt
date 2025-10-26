package de.ollie.carp.vtt.swing.component;

import static org.junit.jupiter.api.Assertions.assertSame;

import de.ollie.carp.vtt.swing.component.EditorButtonPanel.Observer;
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

	@InjectMocks
	private EditorButtonPanel unitUnderTest;

	@Nested
	class prepare {

		@Test
		void returns_itself() {
			assertSame(unitUnderTest, unitUnderTest.prepare());
		}
	}
}
