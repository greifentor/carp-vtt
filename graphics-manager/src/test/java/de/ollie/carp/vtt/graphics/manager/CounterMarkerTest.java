package de.ollie.carp.vtt.graphics.manager;

import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CounterMarkerTest {

	private static final int COUNT = 1701;
	private static final int X = 42;
	private static final int Y = 7;

	@Mock
	private Graphics2D graphics;

	@InjectMocks
	private CounterMarker unitUnderTest;

	@Nested
	class renderCounterMarker_Graphics2D_int_int_int {

		@Test
		void doesTheSettingsInTheRightOrder() {
			// Prepare
			TokenInfo tokenInfo = new TokenInfo(X, Y, -1, -1, null, COUNT);
			// Run
			unitUnderTest.renderCounterMarker(graphics, tokenInfo);
			// Check
			InOrder inOrder = inOrder(graphics);
			inOrder.verify(graphics).setColor(Color.BLACK);
			inOrder.verify(graphics).setStroke(new BasicStroke(1));
			inOrder.verify(graphics).drawRect(X + 3, Y + 3, 15, 12);
			inOrder.verify(graphics).setColor(Color.LIGHT_GRAY);
			inOrder.verify(graphics).fillRect(X + 3, Y + 3, 15, 12);
			inOrder.verify(graphics).setColor(Color.RED);
			inOrder.verify(graphics).setFont(new Font("Serif", Font.BOLD, 12));
		}

		@Test
		void drawTheCounterCorrectly() {
			// Prepare
			TokenInfo tokenInfo = new TokenInfo(X, Y, -1, -1, null, COUNT);
			// Run
			unitUnderTest.renderCounterMarker(graphics, tokenInfo);
			// Check
			verify(graphics, times(1)).drawString("" + COUNT, X + 4, Y + 12);
		}
	}
}
