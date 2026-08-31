package de.ollie.carp.vtt.graphics.manager;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import de.ollie.baselib.util.graphics.ImageFactory;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.ImageObserver;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class TokenDrawerTest {

	private static final int HEIGHT = 4711;
	private static final byte[] IMAGE_BYTES = new byte[] { 1, 2, 3, 4, 5 };
	private static final int WIDTH = 815;
	private static final int X = 42;
	private static final int Y = 7;

	@Mock
	private Graphics2D graphics;

	@Mock
	private Image image;

	@Mock
	private ImageFactory imageFactory;

	@Mock
	private ImageObserver imageObserver;

	@InjectMocks
	private TokenDrawer unitUnderTest;

	@Nested
	class drawToken_Graphics2D_int_int_int_int_byteArr_ImageObserver {

		@Test
		void callsTheDrawImageMethodOfTheGraphicsObjectCorrectly() throws Exception {
			// Prepare
			TokenInfo tokenInfo = new TokenInfo(X, Y, WIDTH, HEIGHT, IMAGE_BYTES, -1);
			when(imageFactory.create(IMAGE_BYTES)).thenReturn(image);
			// Run
			unitUnderTest.drawToken(graphics, tokenInfo, imageObserver);
			// Check
			verify(graphics, times(1)).drawImage(image, X, Y, WIDTH, HEIGHT, imageObserver);
		}
	}
}
