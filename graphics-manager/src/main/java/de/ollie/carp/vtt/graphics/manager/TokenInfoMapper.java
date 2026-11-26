package de.ollie.carp.vtt.graphics.manager;

import de.ollie.carp.vtt.core.service.model.TokenInfoProvider;
import jakarta.inject.Named;
import java.math.BigDecimal;

@Named
class TokenInfoMapper {

	TokenInfo toTokenInfo(TokenInfoProvider token, BigDecimal fieldX, BigDecimal fieldY, int counter) {
		int x = (fieldX.intValue() * GraphicsManager.FIELD_SIZE_IN_PIXELS) + GraphicsManager.OFFSET_IN_PIXELS;
		int y = (fieldY.intValue() * GraphicsManager.FIELD_SIZE_IN_PIXELS) + GraphicsManager.OFFSET_IN_PIXELS;
		int height = GraphicsManager.FIELD_SIZE_IN_PIXELS * token.getTokenSize().getFields();
		int width = GraphicsManager.FIELD_SIZE_IN_PIXELS * token.getTokenSize().getFields();
		return new TokenInfo(x, y, width, height, token.getImage(), counter);
	}
}
