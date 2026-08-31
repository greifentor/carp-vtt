package de.ollie.carp.vtt.graphics.manager;

import de.ollie.carp.vtt.core.service.model.CoordinatesInfoProvider;
import de.ollie.carp.vtt.core.service.model.TokenInfoProvider;
import jakarta.inject.Named;

@Named
class TokenInfoMapper {

	TokenInfo toTokenInfo(TokenInfoProvider token, CoordinatesInfoProvider coordinates, int counter) {
		int x =
			(coordinates.getFieldX().intValue() * GraphicsManager.FIELD_SIZE_IN_PIXELS) + GraphicsManager.OFFSET_IN_PIXELS;
		int y =
			(coordinates.getFieldY().intValue() * GraphicsManager.FIELD_SIZE_IN_PIXELS) + GraphicsManager.OFFSET_IN_PIXELS;
		int height = GraphicsManager.FIELD_SIZE_IN_PIXELS * token.getTokenSize().getFields();
		int width = GraphicsManager.FIELD_SIZE_IN_PIXELS * token.getTokenSize().getFields();
		return new TokenInfo(x, y, width, height, token.getImage(), counter);
	}
}
