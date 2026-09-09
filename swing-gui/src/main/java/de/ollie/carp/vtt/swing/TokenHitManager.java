package de.ollie.carp.vtt.swing;

import de.ollie.carp.vtt.core.service.model.TokenInfoProvider;
import de.ollie.carp.vtt.graphics.manager.GraphicsManager;
import de.ollie.carp.vtt.graphics.manager.model.TokenMap;
import de.ollie.carp.vtt.graphics.manager.model.TokenMap.MapToken;
import de.ollie.carp.vtt.graphics.manager.model.TokenMap.MapTokenId;
import jakarta.inject.Named;
import java.awt.Image;
import java.awt.Rectangle;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import javax.imageio.ImageIO;

@Named
public class TokenHitManager {

	private static final int OFFSET_IN_PIXELS = GraphicsManager.OFFSET_IN_PIXELS;
	private static final int FIELD_SIZE_IN_PIXELS = GraphicsManager.FIELD_SIZE_IN_PIXELS;

	public MapToken getTokenAt(TokenMap tokenMap, int x, int y) {
		for (MapTokenId id : tokenMap.keySet()) {
			MapToken mapToken = tokenMap.get(id);
			TokenInfoProvider token = mapToken.getToken();
			int tokenX = (mapToken.getCoordinates().getFieldX().intValue() * FIELD_SIZE_IN_PIXELS) + OFFSET_IN_PIXELS;
			int tokenY = (mapToken.getCoordinates().getFieldY().intValue() * FIELD_SIZE_IN_PIXELS) + OFFSET_IN_PIXELS;
			try {
				Image tokenImage = ImageIO.read(new ByteArrayInputStream(token.getImage()));
				int w = tokenImage.getWidth(null);
				int h = tokenImage.getHeight(null);
				Rectangle bounds = new Rectangle(tokenX, tokenY, w, h);
				if (bounds.contains(x, y)) {
					return mapToken;
				}
			} catch (IOException ioe) {
				ioe.printStackTrace();
			}
		}
		return null;
	}
}
