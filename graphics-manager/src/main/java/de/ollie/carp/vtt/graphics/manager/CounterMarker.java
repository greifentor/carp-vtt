package de.ollie.carp.vtt.graphics.manager;

import jakarta.inject.Named;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

@Named
class CounterMarker {

	void renderCounterMarker(Graphics2D g, TokenInfo tokenInfo) {
		g.setColor(Color.BLACK);
		g.setStroke(new BasicStroke(1));
		g.drawRect(tokenInfo.x() + 3, tokenInfo.y() + 3, 15, 12);
		g.setColor(Color.LIGHT_GRAY);
		g.fillRect(tokenInfo.x() + 3, tokenInfo.y() + 3, 15, 12);
		g.setColor(Color.RED);
		g.setFont(new Font("Serif", Font.BOLD, 12));
		g.drawString("" + tokenInfo.counter(), tokenInfo.x() + 4, tokenInfo.y() + 12);
	}
}
