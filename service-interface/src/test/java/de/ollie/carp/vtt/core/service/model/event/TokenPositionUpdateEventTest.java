package de.ollie.carp.vtt.core.service.model.event;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import de.ollie.carp.vtt.core.service.model.BattleMap;
import de.ollie.carp.vtt.core.service.model.Coordinates;
import de.ollie.carp.vtt.core.service.model.Party;
import de.ollie.carp.vtt.core.service.model.Scenario;
import de.ollie.carp.vtt.core.service.model.Token;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class TokenPositionUpdateEventTest {

	private static final UUID ID = UUID.randomUUID();

	@Mock
	private Coordinates coordinates;

	@Mock
	private BattleMap battleMap;

	@Mock
	private Party party;

	@Mock
	private Scenario scenario;

	@Mock
	private Token token;

	private TokenPositionUpdateEvent unitUnderTest;

	@BeforeEach
	private void beforeEach() {
		unitUnderTest = new TokenPositionUpdateEvent(ID, token, battleMap, coordinates, party, scenario);
	}

	@Nested
	class constructor_AllArguments {

		@Test
		void throwsAnException_passingANullValueAs_Coordinates() {
			assertThrows(
				IllegalArgumentException.class,
				() -> new TokenPositionUpdateEvent(ID, token, battleMap, null, party, scenario)
			);
		}

		@Test
		void throwsAnException_passingANullValueAs_Id() {
			assertThrows(
				IllegalArgumentException.class,
				() -> new TokenPositionUpdateEvent(null, token, battleMap, coordinates, party, scenario)
			);
		}

		@Test
		void throwsAnException_passingANullValueAs_Map() {
			assertThrows(
				IllegalArgumentException.class,
				() -> new TokenPositionUpdateEvent(ID, token, null, coordinates, party, scenario)
			);
		}

		@Test
		void throwsAnException_passingANullValueAs_Party() {
			assertThrows(
				IllegalArgumentException.class,
				() -> new TokenPositionUpdateEvent(ID, token, battleMap, coordinates, null, scenario)
			);
		}

		@Test
		void throwsAnException_passingANullValueAs_Scenario() {
			assertThrows(
				IllegalArgumentException.class,
				() -> new TokenPositionUpdateEvent(ID, token, battleMap, coordinates, party, null)
			);
		}

		@Test
		void throwsAnException_passingANullValueAs_Token() {
			assertThrows(
				IllegalArgumentException.class,
				() -> new TokenPositionUpdateEvent(ID, null, battleMap, coordinates, party, scenario)
			);
		}
	}

	@Nested
	class IdGetters {

		@Test
		void getId_returnsTheIdOfTheRecord() {
			assertEquals(ID, unitUnderTest.getId());
		}

		@Test
		void getMapId_returnsTheIdOfTheMap() {
			// Prepare
			when(battleMap.getId()).thenReturn(ID);
			// Run & Check
			assertEquals(ID, unitUnderTest.getBattleMapId());
		}

		@Test
		void getPartyId_returnsTheIdOfTheParty() {
			// Prepare
			when(party.getId()).thenReturn(ID);
			// Run & Check
			assertEquals(ID, unitUnderTest.getPartyId());
		}

		@Test
		void getScenarioId_returnsTheIdOfTheScenario() {
			// Prepare
			when(scenario.getId()).thenReturn(ID);
			// Run & Check
			assertEquals(ID, unitUnderTest.getScenarioId());
		}

		@Test
		void getTokenId_returnsTheIdOfTheToken() {
			// Prepare
			when(token.getId()).thenReturn(ID);
			// Run & Check
			assertEquals(ID, unitUnderTest.getTokenId());
		}
	}
}
