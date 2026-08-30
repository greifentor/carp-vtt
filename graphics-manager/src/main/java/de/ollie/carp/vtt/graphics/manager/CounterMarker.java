package de.ollie.carp.vtt.graphics.manager;

import jakarta.inject.Named;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

@Named
public class CounterMarker {

	public void renderCounterMarker(Graphics2D g, int x, int y, int count) {
		g.setColor(Color.BLACK);
		g.setStroke(new BasicStroke(1));
		g.drawRect(x + 3, y + 3, 15, 12);
		g.setColor(Color.LIGHT_GRAY);
		g.fillRect(x + 3, y + 3, 15, 12);
		g.setColor(Color.RED);
		g.setFont(new Font("Serif", Font.BOLD, 12));
		g.drawString("" + count, x + 4, y + 12);
	}
}
