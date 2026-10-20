package de.ollie.carp.vtt.graphics.manager.dnd;

import de.ollie.carp.vtt.core.service.model.TokenSize;
import de.ollie.carp.vtt.graphics.manager.model.TokenPaintManager;
import java.awt.Color;
import java.awt.Graphics2D;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class DndTokenPaintManager implements TokenPaintManager {

	private int currentHitPoints;
	private int maxHitPoints;
	private TokenSize tokenSize;

	@Override
	public void postPaint(Graphics2D graphics, int x, int y) {
		graphics.setColor(Color.GRAY);
		graphics.fillRect(x, y, tokenSize.getFields() * 50, 3);
		graphics.setColor(Color.BLACK);
		graphics.drawRect(x, y, tokenSize.getFields() * 50, 3);
		int hundred = tokenSize.getFields() * 50 - 2;
		int length = (int) (((100.0 / maxHitPoints) * currentHitPoints) * (hundred / 100.0));
		graphics.setColor(Color.GREEN);
		graphics.fillRect(x + 1, y + 1, length, 1);
	}

	@Override
	public void prePaint(Graphics2D graphics, int x, int y) {
		// NOP
	}
}
