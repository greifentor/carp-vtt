package de.ollie.carp.vtt.graphics.manager;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

import de.ollie.carp.vtt.core.service.model.CoordinatesInfoProvider;
import de.ollie.carp.vtt.core.service.model.TokenInfoProvider;
import de.ollie.carp.vtt.graphics.manager.model.TokenMap;
import de.ollie.carp.vtt.graphics.manager.model.TokenMap.MapToken;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.ImageObserver;
import java.io.IOException;
import java.util.Set;
import java.util.function.BiPredicate;
import javax.swing.ImageIcon;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class GraphicsManagerTest {

	private static final int COUNTER = 42;
	private static final BiPredicate<MapToken, MapToken> IS_NOT_SELECTED = (m0, m1) -> false;
	private static final BiPredicate<MapToken, MapToken> IS_SELECTED = (m0, m1) -> true;

	@Mock
	private BattleMapDrawer battleMapDrawer;

	@Mock
	private CoordinatesInfoProvider coordinatesInfoProvider;

	@Mock
	private CounterMarker counterMarker;

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
	private TokenDrawer tokenDrawer;

	@Mock
	private TokenInfo tokenInfo;

	@Mock
	private TokenInfoMapper tokenInfoMapper;

	@Mock
	private TokenInfoProvider tokenInfoProvider;

	@Mock
	private TokenMap tokenMap;

	@Mock
	private SelectedTokenMarker selectedTokenMarker;

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

		@Test
		void callNothingExceptTheBattleMapDrawer() {
			// Prepare
			when(tokenMap.keySet()).thenReturn(Set.of());
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
			verifyNoInteractions(counterMarker, tokenDrawer, tokenInfoMapper, selectedTokenMarker);
		}

		@Test
		void throwsAnException_whenTokenDrawingFails() throws Exception {
			// Prepare
			IOException exception = mock(IOException.class);
			when(mapToken.counter()).thenReturn(COUNTER);
			when(mapToken.token()).thenReturn(tokenInfoProvider);
			doThrow(exception).when(tokenDrawer).drawToken(graphics, tokenInfo, imageObserver);
			when(tokenInfoMapper.toTokenInfo(tokenInfoProvider, coordinatesInfoProvider, COUNTER)).thenReturn(tokenInfo);
			when(tokenMap.keySet()).thenReturn(Set.of(mapToken));
			when(tokenMap.get(mapToken)).thenReturn(coordinatesInfoProvider);
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
			verify(exception, times(1)).printStackTrace();
		}

		@Test
		void callsTheDrawTokenMethodOfTheTokenDrawerCorrectly_noSelectionNoCounter() throws Exception {
			// Prepare
			when(mapToken.counter()).thenReturn(COUNTER);
			when(mapToken.token()).thenReturn(tokenInfoProvider);
			when(tokenInfoMapper.toTokenInfo(tokenInfoProvider, coordinatesInfoProvider, COUNTER)).thenReturn(tokenInfo);
			when(tokenMap.keySet()).thenReturn(Set.of(mapToken));
			when(tokenMap.get(mapToken)).thenReturn(coordinatesInfoProvider);
			// Run
			unitUnderTest.paintBattleMapForScenarioAndParty(
				graphics,
				tokenMap,
				mapToken,
				mapImage,
				imageObserver,
				IS_NOT_SELECTED
			);
			// Check
			verify(tokenDrawer, times(1)).drawToken(graphics, tokenInfo, imageObserver);
			verifyNoInteractions(counterMarker, selectedTokenMarker);
		}

		@Test
		void callsTheCounterMarkerCorrectly_noSelection() {
			// Prepare
			when(mapToken.counter()).thenReturn(COUNTER);
			when(mapToken.token()).thenReturn(tokenInfoProvider);
			when(tokenInfoMapper.toTokenInfo(tokenInfoProvider, coordinatesInfoProvider, COUNTER)).thenReturn(tokenInfo);
			when(tokenMap.hasTokenMoreThanOneTimes(tokenInfoProvider)).thenReturn(true);
			when(tokenMap.keySet()).thenReturn(Set.of(mapToken));
			when(tokenMap.get(mapToken)).thenReturn(coordinatesInfoProvider);
			// Run
			unitUnderTest.paintBattleMapForScenarioAndParty(
				graphics,
				tokenMap,
				mapToken,
				mapImage,
				imageObserver,
				IS_NOT_SELECTED
			);
			// Check
			verify(counterMarker, times(1)).renderCounterMarker(graphics, tokenInfo);
			verifyNoInteractions(selectedTokenMarker);
		}

		@Test
		void callsTheSelectedTokenMarkerCorrectly_noCounter() {
			// Prepare
			when(mapToken.counter()).thenReturn(COUNTER);
			when(mapToken.token()).thenReturn(tokenInfoProvider);
			when(tokenInfoMapper.toTokenInfo(tokenInfoProvider, coordinatesInfoProvider, COUNTER)).thenReturn(tokenInfo);
			when(tokenMap.keySet()).thenReturn(Set.of(mapToken));
			when(tokenMap.get(mapToken)).thenReturn(coordinatesInfoProvider);
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
			verify(selectedTokenMarker, times(1)).renderSelectedMarker(graphics, tokenInfo);
			verifyNoInteractions(counterMarker);
		}
	}
}
