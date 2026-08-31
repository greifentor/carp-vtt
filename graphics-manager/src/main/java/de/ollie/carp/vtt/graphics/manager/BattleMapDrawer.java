package de.ollie.carp.vtt.graphics.manager;

import jakarta.inject.Named;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.ImageObserver;

@Named
class BattleMapDrawer {

	void drawBattleMap(Graphics2D graphics, Image image, ImageObserver imageObserver) {
		graphics.drawImage(image, 0, 0, imageObserver);
	}
}
