package de.ollie.carp.vtt.service.impl;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import de.ollie.carp.vtt.core.service.model.Coordinates;
import de.ollie.carp.vtt.core.service.model.Map;
import de.ollie.carp.vtt.core.service.model.Party;
import de.ollie.carp.vtt.core.service.model.Scenario;
import de.ollie.carp.vtt.core.service.model.Token;
import de.ollie.carp.vtt.core.service.model.event.TokenPositionUpdateEvent;
import de.ollie.carp.vtt.core.service.port.persistence.TokenUpdatePersistencePort;
import java.util.UUID;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class TokenPositionServiceImplTest {

	private static final UUID MAP_ID = UUID.randomUUID();
	private static final UUID PARTY_ID = UUID.randomUUID();
	private static final UUID SCENARIO_ID = UUID.randomUUID();
	private static final UUID TOKEN_ID = UUID.randomUUID();

	@Mock
	private TokenUpdatePersistencePort tokenUpdatePort;

	@InjectMocks
	private TokenPositionServiceImpl unitUnderTest;

	@Nested
	class updateTokenPosition_TokenPositionUpdateEvent {

		@Mock
		private Coordinates coordinates;

		@Mock
		private Map map;

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
			when(map.getId()).thenReturn(MAP_ID);
			when(party.getId()).thenReturn(PARTY_ID);
			when(scenario.getId()).thenReturn(SCENARIO_ID);
			when(token.getId()).thenReturn(TOKEN_ID);
			// Run
			unitUnderTest.updateTokenPosition(new TokenPositionUpdateEvent(token, map, coordinates, party, scenario));
			// Check
			verify(tokenUpdatePort, times(1)).updateTokenPosition(TOKEN_ID, MAP_ID, PARTY_ID, SCENARIO_ID, coordinates);
		}
	}
}
