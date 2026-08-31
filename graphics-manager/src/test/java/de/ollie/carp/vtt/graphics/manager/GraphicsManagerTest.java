package de.ollie.carp.vtt.graphics.manager;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import de.ollie.carp.vtt.graphics.manager.model.TokenMap;
import de.ollie.carp.vtt.graphics.manager.model.TokenMap.MapToken;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.ImageObserver;
import java.util.function.BiFunction;
import javax.swing.ImageIcon;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class GraphicsManagerTest {

	private static final BiFunction<MapToken, MapToken, Boolean> IS_NOT_SELECTED = (m0, m1) -> false;
	private static final BiFunction<MapToken, MapToken, Boolean> IS_SELECTED = (m0, m1) -> true;

	@Mock
	private BattleMapDrawer battleMapDrawer;

	@Mock
	private Graphics2D graphics;

	@Mock
	private Image image;

	@Mock
	private ImageObserver imageObserver;

	@Mock
	private ImageIcon mapImage;

	@Mock
	private MapToken mapToken;

	@Mock
	private TokenMap tokenMap;

	@InjectMocks
	private GraphicsManager unitUnderTest;

	@Nested
	class paintBattleMapForScenarioAndParty_Graphics2D_TokenMap_MapToken_ImageIcon_ImageObserver_BiFunctionMapTokenMapTokenBoolean {

		@Test
		void callsTheBattleMapDrawerCorrectly() {
			// Prepare
			when(mapImage.getImage()).thenReturn(image);
			// Run
			unitUnderTest.paintBattleMapForScenarioAndParty(
				graphics,
				tokenMap,
				mapToken,
				mapImage,
				imageObserver,
				IS_SELECTED
			);
			// Check
			verify(battleMapDrawer, times(1)).drawBattleMap(graphics, image, imageObserver);
		}
	}
}
