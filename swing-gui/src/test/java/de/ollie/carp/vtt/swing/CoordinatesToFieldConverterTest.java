package de.ollie.carp.vtt.swing;

import static org.junit.jupiter.api.Assertions.assertEquals;

import de.ollie.carp.vtt.core.service.model.Coordinates;
import java.math.BigDecimal;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CoordinatesToFieldConverterTest {

	@InjectMocks
	private CoordinatesToFieldConverter unitUnderTest;

	@Nested
	class getFieldCoordinates_int_int {

		@Test
		void returnsTheCorrectFieldCoordinates() {
			// Prepare
			int x = 150;
			int y = 250;
			Coordinates expected = new Coordinates().setFieldX(new BigDecimal("2.0")).setFieldY(new BigDecimal("4.0"));
			// Run & Check
			assertEquals(expected, unitUnderTest.getFieldCoordinates(x, y));
		}
	}
}
