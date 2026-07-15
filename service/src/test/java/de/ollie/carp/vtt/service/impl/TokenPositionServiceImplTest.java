package de.ollie.carp.vtt.service.impl;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import de.ollie.carp.vtt.core.service.model.BattleMap;
import de.ollie.carp.vtt.core.service.model.Coordinates;
import de.ollie.carp.vtt.core.service.model.Party;
import de.ollie.carp.vtt.core.service.model.Scenario;
import de.ollie.carp.vtt.core.service.model.Token;
import de.ollie.carp.vtt.core.service.model.TokenData;
import de.ollie.carp.vtt.core.service.model.event.TokenPositionUpdateEvent;
import de.ollie.carp.vtt.core.service.port.persistence.TokenUpdatePersistencePort;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class TokenPositionServiceImplTest {

	private static final UUID ID = UUID.randomUUID();
	private static final UUID MAP_ID = UUID.randomUUID();
	private static final UUID PARTY_ID = UUID.randomUUID();
	private static final UUID SCENARIO_ID = UUID.randomUUID();
	private static final UUID TOKEN_ID = UUID.randomUUID();

	@Mock
	private TokenUpdatePersistencePort tokenUpdatePort;

	@InjectMocks
	private TokenPositionServiceImpl unitUnderTest;

	@Nested
	class findAllBy_Map_Party_Scenario {

		@Mock
		private BattleMap battleMap;

		@Mock
		private Party party;

		@Mock
		private Scenario scenario;

		@Test
		void throwsAnException_passingANullValueAs_Map() {
			assertThrows(IllegalArgumentException.class, () -> unitUnderTest.findAllBy(null, party, scenario));
		}

		@Test
		void throwsAnException_passingANullValueAs_Party() {
			assertThrows(IllegalArgumentException.class, () -> unitUnderTest.findAllBy(battleMap, null, scenario));
		}

		@Test
		void throwsAnException_passingANullValueAs_Scenario() {
			assertThrows(IllegalArgumentException.class, () -> unitUnderTest.findAllBy(battleMap, party, null));
		}

		@Test
		void callsPersistencePortMethodCorrectly() {
			// Prepare
			List<TokenData> expected = List.of();
			when(battleMap.getId()).thenReturn(MAP_ID);
			when(party.getId()).thenReturn(PARTY_ID);
			when(scenario.getId()).thenReturn(SCENARIO_ID);
			when(tokenUpdatePort.findAllByMapPartyScenario(MAP_ID, PARTY_ID, SCENARIO_ID)).thenReturn(expected);
			// Run
			List<TokenData> returned = unitUnderTest.findAllBy(battleMap, party, scenario);
			// Check
			assertSame(expected, returned);
		}
	}

	@Nested
	class updateTokenPosition_TokenPositionUpdateEvent {

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

		@Test
		void throwsAnException_passingANullValueAs_TokenPositionUpdateEvent() {
			assertThrows(IllegalArgumentException.class, () -> unitUnderTest.updateTokenPosition(null));
		}

		@Test
		void callsThePersistencePortMethodCorrectly() {
			// Prepare
			when(battleMap.getId()).thenReturn(MAP_ID);
			when(party.getId()).thenReturn(PARTY_ID);
			when(scenario.getId()).thenReturn(SCENARIO_ID);
			when(token.getId()).thenReturn(TOKEN_ID);
			// Run
			unitUnderTest.updateTokenPosition(
				new TokenPositionUpdateEvent(ID, token, battleMap, coordinates, party, scenario)
			);
			// Check
			verify(tokenUpdatePort, times(1)).updateTokenPosition(ID, TOKEN_ID, MAP_ID, PARTY_ID, SCENARIO_ID, coordinates);
		}
	}
}
