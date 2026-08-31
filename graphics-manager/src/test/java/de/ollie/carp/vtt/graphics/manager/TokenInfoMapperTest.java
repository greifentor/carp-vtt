package de.ollie.carp.vtt.graphics.manager;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import de.ollie.carp.vtt.core.service.model.Coordinates;
import de.ollie.carp.vtt.core.service.model.TokenInfoProvider;
import de.ollie.carp.vtt.core.service.model.TokenSize;
import java.math.BigDecimal;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class TokenInfoMapperTest {

	private static final int COUNTER = 8;
	private static final BigDecimal FIELD_X = new BigDecimal(2);
	private static final BigDecimal FIELD_Y = new BigDecimal(3);
	private static final int HEIGHT = 4711;
	private static final byte[] IMAGE = new byte[] { 1, 2, 3, 4, 5 };
	private static final TokenSize TOKEN_SIZE = TokenSize.MEDIUM;
	private static final int WIDTH = 815;
	private static final int X = 42;
	private static final int Y = 7;

	@InjectMocks
	private TokenInfoMapper unitUnderTest;

	@Nested
	class toTokenInfo_TokenInfoProvider_CoordinatesInfoProvider_int {

		@Test
		void returnsACorrectTokenInfo() {
			// Prepare
			Coordinates coordinates = new Coordinates().setFieldX(FIELD_X).setFieldY(FIELD_Y);
			TokenInfoProvider token = mock(TokenInfoProvider.class);
			when(token.getImage()).thenReturn(IMAGE);
			when(token.getTokenSize()).thenReturn(TOKEN_SIZE);
			TokenInfo expected = new TokenInfo(
				FIELD_X.intValue() * GraphicsManager.FIELD_SIZE_IN_PIXELS + GraphicsManager.OFFSET_IN_PIXELS,
				FIELD_Y.intValue() * GraphicsManager.FIELD_SIZE_IN_PIXELS + GraphicsManager.OFFSET_IN_PIXELS,
				TOKEN_SIZE.getFields() * GraphicsManager.FIELD_SIZE_IN_PIXELS,
				TOKEN_SIZE.getFields() * GraphicsManager.FIELD_SIZE_IN_PIXELS,
				IMAGE,
				COUNTER
			);
			// Run & Check
			assertEquals(expected, unitUnderTest.toTokenInfo(token, coordinates, COUNTER));
		}
	}
}
