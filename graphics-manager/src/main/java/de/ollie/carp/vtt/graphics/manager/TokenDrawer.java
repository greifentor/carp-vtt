package de.ollie.carp.vtt.graphics.manager;

import de.ollie.baselib.util.graphics.ImageFactory;
import jakarta.inject.Named;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.ImageObserver;
import java.io.IOException;
import lombok.RequiredArgsConstructor;

@Named
@RequiredArgsConstructor
class TokenDrawer {

	private final ImageFactory imageFactory;

	void drawToken(Graphics2D graphics, TokenInfo tokenInfo, ImageObserver imageObserver) throws IOException {
		Image tokenImage = imageFactory.create(tokenInfo.image());
		graphics.drawImage(tokenImage, tokenInfo.x(), tokenInfo.y(), tokenInfo.width(), tokenInfo.height(), imageObserver);
	}
}
