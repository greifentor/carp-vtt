package de.ollie.carp.vtt.persistence.jpa;

import static org.junit.jupiter.api.Assertions.assertThrows;

import de.ollie.carp.vtt.core.service.model.Coordinates;
import java.util.UUID;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class TokenUpdatePersistenceJpaAdapterTest {

	private static final UUID MAP_ID = UUID.randomUUID();
	private static final UUID PARTY_ID = UUID.randomUUID();
	private static final UUID SCENARIO_ID = UUID.randomUUID();
	private static final UUID TOKEN_ID = UUID.randomUUID();

	@Mock
	private Coordinates coordinates;

	@InjectMocks
	private TokenUpdatePersistenceJpaAdapter unitUnderTest;

	@Nested
	class updateTokenPosition_UUID_UUID_UUID_UUID_Coordinates {

		@Test
		void throwsAnException_passingANullValueAs_Coordinates() {
			assertThrows(
				IllegalArgumentException.class,
				() -> unitUnderTest.updateTokenPosition(TOKEN_ID, MAP_ID, PARTY_ID, SCENARIO_ID, null)
			);
		}

		@Test
		void throwsAnException_passingANullValueAs_MapId() {
			assertThrows(
				IllegalArgumentException.class,
				() -> unitUnderTest.updateTokenPosition(TOKEN_ID, null, PARTY_ID, SCENARIO_ID, coordinates)
			);
		}

		@Test
		void throwsAnException_passingANullValueAs_PartyId() {
			assertThrows(
				IllegalArgumentException.class,
				() -> unitUnderTest.updateTokenPosition(TOKEN_ID, MAP_ID, null, SCENARIO_ID, coordinates)
			);
		}

		@Test
		void throwsAnException_passingANullValueAs_ScenarioId() {
			assertThrows(
				IllegalArgumentException.class,
				() -> unitUnderTest.updateTokenPosition(TOKEN_ID, MAP_ID, PARTY_ID, null, coordinates)
			);
		}

		@Test
		void throwsAnException_passingANullValueAs_TokenId() {
			assertThrows(
				IllegalArgumentException.class,
				() -> unitUnderTest.updateTokenPosition(null, MAP_ID, PARTY_ID, SCENARIO_ID, coordinates)
			);
		}
	}
}
