package de.ollie.carp.vtt.graphics.manager;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

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
class BattleMapDrawerTest {

	@Mock
	private Image image;

	@Mock
	private ImageObserver imageObserver;

	@Mock
	private Graphics2D graphics;

	@InjectMocks
	private BattleMapDrawer unitUnderTest;

	@Nested
	class drawBattleMap_Graphics2D_Image_ImageObserver {

		@Test
		void callsTheDrawImageMethodCorrectly() {
			// Run
			unitUnderTest.drawBattleMap(graphics, image, imageObserver);
			// Check
			verify(graphics, times(1)).drawImage(image, 0, 0, imageObserver);
		}
	}
}
