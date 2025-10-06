package de.ollie.carp.vtt.swing;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import de.ollie.carp.vtt.swing.SwingConstants;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class SwingConstantsTest {

	@Nested
	class constructor {

		@Test
		void throwsAnException_whenCalled() {
			assertThrows(UnsupportedOperationException.class, SwingConstants::new);
		}
	}

	@Nested
	class constants {

		@Test
		void hgapHasTheCorrectValue() {
			assertEquals(3, SwingConstants.HGAP);
		}

		@Test
		void vgapHasTheCorrectValue() {
			assertEquals(3, SwingConstants.VGAP);
		}
	}
}
