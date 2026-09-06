package de.ollie.carp.vtt.graphics.manager.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import de.ollie.carp.vtt.core.service.model.CoordinatesInfoProvider;
import de.ollie.carp.vtt.core.service.model.TokenInfoProvider;
import de.ollie.carp.vtt.graphics.manager.model.TokenMap.MapToken;
import de.ollie.carp.vtt.graphics.manager.model.TokenMap.MapTokenId;
import java.util.UUID;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class TokenMapTest {

	private static final MapTokenId ID = new MapTokenId(UUID.randomUUID());

	@Mock
	private CoordinatesInfoProvider coordinates;

	@Mock
	private MapToken mapToken;

	@InjectMocks
	private TokenMap unitUnderTest;

	@Nested
	class clear {

		@Test
		void worksWithAnEmptyTokenMap() {
			// Prepare
			unitUnderTest.put(ID, mapToken);
			// Run
			unitUnderTest.clear();
			// Check
			assertTrue(unitUnderTest.keySet().isEmpty());
		}

		@Test
		void clearsTheTokenMap() {
			// Run
			unitUnderTest.clear();
			// Check
			assertTrue(unitUnderTest.keySet().isEmpty());
		}
	}

	@Nested
	class getNextCounterFor_TokenInfoProvider {

		private static final UUID TOKEN_ID = UUID.randomUUID();
		private static final UUID TOKEN_ID_2 = UUID.randomUUID();

		@Mock
		private TokenInfoProvider token;

		@Test
		void returns1_withAnEmptyTokenMap() {
			// Run & Check
			assertEquals(1, unitUnderTest.getNextCounterFor(token));
		}

		@Test
		void returns1_whenTheTokenIsNotInTheTokenMap() {
			// Prepare
			TokenInfoProvider token2 = mock(TokenInfoProvider.class);
			when(mapToken.getToken()).thenReturn(token);
			when(token.getId()).thenReturn(TOKEN_ID);
			when(token2.getId()).thenReturn(TOKEN_ID_2);
			unitUnderTest.put(ID, mapToken);
			// Run & Check
			assertEquals(1, unitUnderTest.getNextCounterFor(token2));
		}

		@Test
		void returns2_whenTheTokenIsInTheTokenMap() {
			// Prepare
			TokenInfoProvider token2 = mock(TokenInfoProvider.class);
			when(mapToken.getToken()).thenReturn(token);
			when(token.getId()).thenReturn(TOKEN_ID);
			when(token2.getId()).thenReturn(TOKEN_ID);
			unitUnderTest.put(ID, mapToken);
			// Run & Check
			assertEquals(2, unitUnderTest.getNextCounterFor(token2));
		}
	}

	@Nested
	class hasTokenMoreThanOneTimes_TokenInfoProvider {

		private static final UUID TOKEN_ID = UUID.randomUUID();
		private static final UUID TOKEN_ID_2 = UUID.randomUUID();

		@Mock
		private TokenInfoProvider token;

		@Mock
		private TokenInfoProvider token2;

		@Test
		void returnsFalse_withAnEmptyTokenMap() {
			// Run & Check
			assertFalse(unitUnderTest.hasTokenMoreThanOneTimes(token));
		}

		@Test
		void returnsFalse_whenTheTokenIsNotInTheTokenMap() {
			// Prepare
			when(mapToken.getToken()).thenReturn(token);
			when(token.getId()).thenReturn(TOKEN_ID);
			when(token2.getId()).thenReturn(TOKEN_ID_2);
			unitUnderTest.put(ID, mapToken);
			// Run & Check
			assertFalse(unitUnderTest.hasTokenMoreThanOneTimes(token2));
		}

		@Test
		void returnsTrue_whenTheTokenIsInTheTokenMap() {
			// Prepare
			MapToken mapToken2 = mock(MapToken.class);
			TokenInfoProvider token3 = mock(TokenInfoProvider.class);
			when(mapToken.getToken()).thenReturn(token);
			when(mapToken2.getToken()).thenReturn(token3);
			when(token.getId()).thenReturn(TOKEN_ID);
			when(token3.getId()).thenReturn(TOKEN_ID);
			when(token2.getId()).thenReturn(TOKEN_ID);
			unitUnderTest.put(ID, mapToken);
			unitUnderTest.put(new MapTokenId(UUID.randomUUID()), mapToken2);
			// Run & Check
			assertTrue(unitUnderTest.hasTokenMoreThanOneTimes(token2));
		}
	}

	@Nested
	class put_UUID_MapToken {

		@Test
		void throwsAmException_passingANullValueAsKey() {
			assertThrows(IllegalArgumentException.class, () -> unitUnderTest.put(null, mapToken));
		}

		@Test
		void throwsAmException_passingANullValueAsMapToken() {
			assertThrows(IllegalArgumentException.class, () -> unitUnderTest.put(ID, null));
		}

		@Test
		void keyIsPresent() {
			// Prepare
			unitUnderTest.put(ID, mapToken);
			// Run & Check
			assertTrue(unitUnderTest.keySet().contains(ID));
		}

		@Test
		void storesTheMapTokenCorretly() {
			// Prepare
			unitUnderTest.put(ID, mapToken);
			// Run & Check
			assertSame(mapToken, unitUnderTest.get(ID));
		}
	}

	@Nested
	class putCoordinates_UUID_CoordinatesInfoProvider {

		@Test
		void throwsAnException_passingANullValueAsKey() {
			assertThrows(IllegalArgumentException.class, () -> unitUnderTest.putCoordinates(null, coordinates));
		}

		@Test
		void throwsAnException_passingANullValueAsMapToken() {
			assertThrows(IllegalArgumentException.class, () -> unitUnderTest.putCoordinates(ID, null));
		}

		@Test
		void throwsAnException_passingAnIdWhichIsNotAlreadyStored() {
			assertThrows(IllegalArgumentException.class, () -> unitUnderTest.putCoordinates(ID, coordinates));
		}

		@Test
		void changesTheCoordinatesCorrectly() {
			// Prepare
			unitUnderTest.put(ID, mapToken);
			// Run
			unitUnderTest.putCoordinates(ID, coordinates);
			// Check
			verify(mapToken, times(1)).setCoordinates(coordinates);
		}
	}
}
