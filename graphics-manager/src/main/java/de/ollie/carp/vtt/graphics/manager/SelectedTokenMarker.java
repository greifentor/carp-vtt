package de.ollie.carp.vtt.graphics.manager;

import jakarta.inject.Named;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

@Named
public class SelectedTokenMarker {

	public void renderSelectedMarker(Graphics2D g, int x, int y, int width, int height) {
		g.setColor(Color.YELLOW);
		g.setStroke(new BasicStroke(3));
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g.drawArc(x, y, width, height, 0, 360);
	}
}
