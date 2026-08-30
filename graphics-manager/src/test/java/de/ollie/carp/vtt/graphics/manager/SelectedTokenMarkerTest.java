package de.ollie.carp.vtt.graphics.manager;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class SelectedTokenMarkerTest {

	private static final int HEIGHT = 4711;
	private static final int WIDTH = 815;
	private static final int X = 42;
	private static final int Y = 7;

	@Mock
	private Graphics2D graphics;

	@InjectMocks
	private SelectedTokenMarker unitUnderTest;

	@Nested
	class renderSelectedMarker_Graphics2D_int_int_int_int {

		@Test
		void drawsTheSelectMarkerCorrectly() {
			// Run
			unitUnderTest.renderSelectedMarker(graphics, X, Y, WIDTH, HEIGHT);
			// Check
			verify(graphics, times(1)).drawArc(X, Y, WIDTH, HEIGHT, 0, 360);
		}

		@Test
		void setTheCorrectColor() {
			// Run
			unitUnderTest.renderSelectedMarker(graphics, X, Y, WIDTH, HEIGHT);
			// Check
			verify(graphics, times(1)).setColor(Color.YELLOW);
		}

		@Test
		void setTheCorrectRenderingHints() {
			// Run
			unitUnderTest.renderSelectedMarker(graphics, X, Y, WIDTH, HEIGHT);
			// Check
			verify(graphics, times(1)).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		}

		@Test
		void setTheCorrectStroke() {
			// Run
			unitUnderTest.renderSelectedMarker(graphics, X, Y, WIDTH, HEIGHT);
			// Check
			verify(graphics, times(1)).setStroke(new BasicStroke(3));
		}
	}
}
