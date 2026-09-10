package de.ollie.carp.vtt.swing;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import de.ollie.carp.vtt.core.service.model.Coordinates;
import de.ollie.carp.vtt.core.service.model.TokenInfoProvider;
import de.ollie.carp.vtt.graphics.manager.model.TokenMap;
import de.ollie.carp.vtt.graphics.manager.model.TokenMap.MapToken;
import de.ollie.carp.vtt.graphics.manager.model.TokenMap.MapTokenId;
import java.io.IOException;
import java.util.Set;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class TokenHitManagerTest {

	@Mock
	private TokenHitDetector tokenHitDetector;

	@InjectMocks
	private TokenHitManager unitUnderTest;

	@Nested
	class getTokenAt_TokenMap_int_int {

		private static final int X = 2;
		private static final int Y = 3;

		@Mock
		private Coordinates coordinates;

		@Mock
		private MapToken mapToken;

		@Mock
		private MapTokenId mapTokenId;

		@Mock
		private TokenInfoProvider token;

		@Mock
		private TokenMap tokenMap;

		@Test
		void throwsAnException_passingTokenMapAsNullValue() {
			assertThrows(IllegalArgumentException.class, () -> unitUnderTest.getTokenAt(null, X, Y));
		}

		@Test
		void returnsNull_whenTokenMapIsEmpty() {
			// Run & Check
			assertNull(unitUnderTest.getTokenAt(tokenMap, X, Y));
		}

		@Test
		void returnsNullWhenMapToken_doesNotMatch() throws Exception {
			// Assert
			when(tokenHitDetector.hits(mapToken, X, Y)).thenReturn(false);
			when(tokenMap.get(mapTokenId)).thenReturn(mapToken);
			when(tokenMap.keySet()).thenReturn(Set.of(mapTokenId));
			// Run & Check
			assertNull(unitUnderTest.getTokenAt(tokenMap, X, Y));
		}

		@Test
		void returnsNullWhenTokenHitDetectorThrowsAnException() throws Exception {
			// Assert
			when(tokenHitDetector.hits(mapToken, X, Y)).thenThrow(new IOException());
			when(tokenMap.get(mapTokenId)).thenReturn(mapToken);
			when(tokenMap.keySet()).thenReturn(Set.of(mapTokenId));
			// Run & Check
			assertNull(unitUnderTest.getTokenAt(tokenMap, X, Y));
		}

		@Test
		void returnsTheHitMapToken_whenMapTokenIsHit() throws Exception {
			// Assert
			when(tokenHitDetector.hits(mapToken, X, Y)).thenReturn(true);
			when(tokenMap.get(mapTokenId)).thenReturn(mapToken);
			when(tokenMap.keySet()).thenReturn(Set.of(mapTokenId));
			// Run & Check
			assertSame(mapToken, unitUnderTest.getTokenAt(tokenMap, X, Y));
		}
	}
}
