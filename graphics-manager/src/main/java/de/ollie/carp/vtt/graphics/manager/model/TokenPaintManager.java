package de.ollie.carp.vtt.graphics.manager.model;

import java.awt.Graphics2D;

public interface TokenPaintManager {
	default void postPaint(Graphics2D graphics, int x, int y) {}

	default void prePaint(Graphics2D graphics, int x, int y) {}
}
