package de.ollie.carp.vtt.graphics.manager;

import jakarta.inject.Named;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

@Named
class SelectedTokenMarker {

	void renderSelectedMarker(Graphics2D g, TokenInfo tokenInfo) {
		g.setColor(Color.YELLOW);
		g.setStroke(new BasicStroke(3));
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g.drawArc(tokenInfo.x(), tokenInfo.y(), tokenInfo.width(), tokenInfo.height(), 0, 360);
	}
}
