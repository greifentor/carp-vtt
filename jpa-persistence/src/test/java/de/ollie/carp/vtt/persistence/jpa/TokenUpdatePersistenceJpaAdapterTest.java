package de.ollie.carp.vtt.persistence.jpa;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import de.ollie.carp.vtt.core.service.UuidService;
import de.ollie.carp.vtt.core.service.model.Coordinates;
import de.ollie.carp.vtt.core.service.model.Token;
import de.ollie.carp.vtt.core.service.model.TokenData;
import de.ollie.carp.vtt.persistence.jpa.dbo.BattleMapDbo;
import de.ollie.carp.vtt.persistence.jpa.dbo.PartyDbo;
import de.ollie.carp.vtt.persistence.jpa.dbo.ScenarioDbo;
import de.ollie.carp.vtt.persistence.jpa.dbo.TokenDbo;
import de.ollie.carp.vtt.persistence.jpa.dbo.TokenMapPartyScenarioDbo;
import de.ollie.carp.vtt.persistence.jpa.mapper.TokenDboMapper;
import de.ollie.carp.vtt.persistence.jpa.repository.BattleMapDboRepository;
import de.ollie.carp.vtt.persistence.jpa.repository.PartyDboRepository;
import de.ollie.carp.vtt.persistence.jpa.repository.ScenarioDboRepository;
import de.ollie.carp.vtt.persistence.jpa.repository.TokenDboRepository;
import de.ollie.carp.vtt.persistence.jpa.repository.TokenMapPartyScenarioDboRepository;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class TokenUpdatePersistenceJpaAdapterTest {

	private static final BigDecimal FIELD_X = new BigDecimal(1701);
	private static final BigDecimal FIELD_Y = new BigDecimal(4711);
	private static final UUID ID = UUID.randomUUID();
	private static final UUID MAP_ID = UUID.randomUUID();
	private static final UUID PARTY_ID = UUID.randomUUID();
	private static final UUID SCENARIO_ID = UUID.randomUUID();
	private static final UUID TOKEN_ID = UUID.randomUUID();

	@Mock
	private Coordinates coordinates;

	@Mock
	private BattleMapDbo battleMapDbo;

	@Mock
	private PartyDbo partyDbo;

	@Mock
	private ScenarioDbo scenarioDbo;

	@Mock
	private TokenDbo tokenDbo;

	@Mock
	private BattleMapDboRepository battleMapDboRepository;

	@Mock
	private PartyDboRepository partyDboRepository;

	@Mock
	private ScenarioDboRepository scenarioDboRepository;

	@Mock
	private TokenMapPartyScenarioDboRepository tokenMapPartyScenarioDboRepository;

	@Mock
	private TokenDboMapper tokenDboMapper;

	@Mock
	private TokenDboRepository tokenDboRepository;

	@Mock
	private UuidService uuidService;

	@InjectMocks
	private TokenUpdatePersistenceJpaAdapter unitUnderTest;

	@Nested
	class findAllBy_UUID_UUID_UUID {

		@Test
		void throwsAnException_passingANullValueAs_MapId() {
			assertThrows(
				IllegalArgumentException.class,
				() -> unitUnderTest.findAllByMapPartyScenario(null, PARTY_ID, SCENARIO_ID)
			);
		}

		@Test
		void throwsAnException_passingANullValueAs_PartyId() {
			assertThrows(
				IllegalArgumentException.class,
				() -> unitUnderTest.findAllByMapPartyScenario(MAP_ID, null, SCENARIO_ID)
			);
		}

		@Test
		void throwsAnException_passingANullValueAs_ScenarioId() {
			assertThrows(
				IllegalArgumentException.class,
				() -> unitUnderTest.findAllByMapPartyScenario(MAP_ID, PARTY_ID, null)
			);
		}

		@Test
		void returnsACorrectListOfModels() {
			// Prepare
			Token token = mock(Token.class);
			TokenMapPartyScenarioDbo dbo = new TokenMapPartyScenarioDbo()
				.setFieldX(FIELD_X)
				.setFieldY(FIELD_Y)
				.setId(ID)
				.setBattleMap(battleMapDbo)
				.setParty(partyDbo)
				.setScenario(scenarioDbo)
				.setToken(tokenDbo);
			TokenData data = new TokenData()
				.setCoordinates(new Coordinates().setFieldX(FIELD_X).setFieldY(FIELD_Y))
				.setId(ID)
				.setToken(token);
			List<TokenData> expected = List.of(data);
			when(battleMapDboRepository.findById(MAP_ID)).thenReturn(Optional.of(battleMapDbo));
			when(partyDboRepository.findById(PARTY_ID)).thenReturn(Optional.of(partyDbo));
			when(scenarioDboRepository.findById(SCENARIO_ID)).thenReturn(Optional.of(scenarioDbo));
			when(tokenDboMapper.toModel(tokenDbo)).thenReturn(token);
			when(
				tokenMapPartyScenarioDboRepository.findAllByAndBattleMapAndPartyAndScenario(battleMapDbo, partyDbo, scenarioDbo)
			)
				.thenReturn(List.of(dbo));
			// Run
			List<TokenData> returned = unitUnderTest.findAllByMapPartyScenario(MAP_ID, PARTY_ID, SCENARIO_ID);
			// Check
			assertEquals(expected, returned);
		}
	}

	@Nested
	class updateTokenPosition_UUID_UUID_UUID_UUID_Coordinates {

		@Test
		void throwsAnException_passingANullValueAs_Coordinates() {
			assertThrows(
				IllegalArgumentException.class,
				() -> unitUnderTest.updateTokenPosition(ID, TOKEN_ID, MAP_ID, PARTY_ID, SCENARIO_ID, null)
			);
		}

		@Test
		void throwsAnException_passingANullValueAs_Id() {
			assertThrows(
				IllegalArgumentException.class,
				() -> unitUnderTest.updateTokenPosition(null, TOKEN_ID, MAP_ID, PARTY_ID, SCENARIO_ID, coordinates)
			);
		}

		@Test
		void throwsAnException_passingANullValueAs_MapId() {
			assertThrows(
				IllegalArgumentException.class,
				() -> unitUnderTest.updateTokenPosition(ID, TOKEN_ID, null, PARTY_ID, SCENARIO_ID, coordinates)
			);
		}

		@Test
		void throwsAnException_passingANullValueAs_PartyId() {
			assertThrows(
				IllegalArgumentException.class,
				() -> unitUnderTest.updateTokenPosition(ID, TOKEN_ID, MAP_ID, null, SCENARIO_ID, coordinates)
			);
		}

		@Test
		void throwsAnException_passingANullValueAs_ScenarioId() {
			assertThrows(
				IllegalArgumentException.class,
				() -> unitUnderTest.updateTokenPosition(ID, TOKEN_ID, MAP_ID, PARTY_ID, null, coordinates)
			);
		}

		@Test
		void throwsAnException_passingANullValueAs_TokenId() {
			assertThrows(
				IllegalArgumentException.class,
				() -> unitUnderTest.updateTokenPosition(ID, null, MAP_ID, PARTY_ID, SCENARIO_ID, coordinates)
			);
		}

		@Test
		void callsTheRepositoryMethodCorrectly_forAnUpdate() {
			// Prepare
			TokenMapPartyScenarioDbo dbo = new TokenMapPartyScenarioDbo()
				.setFieldX(FIELD_X.add(new BigDecimal(1)))
				.setFieldY(FIELD_Y.add(new BigDecimal(1)))
				.setId(ID)
				.setBattleMap(battleMapDbo)
				.setParty(partyDbo)
				.setScenario(scenarioDbo)
				.setToken(tokenDbo);
			when(tokenMapPartyScenarioDboRepository.findById(ID)).thenReturn(Optional.of(dbo));
			when(coordinates.getFieldX()).thenReturn(FIELD_X);
			when(coordinates.getFieldY()).thenReturn(FIELD_Y);
			when(battleMapDboRepository.findById(MAP_ID)).thenReturn(Optional.of(battleMapDbo));
			when(partyDboRepository.findById(PARTY_ID)).thenReturn(Optional.of(partyDbo));
			when(scenarioDboRepository.findById(SCENARIO_ID)).thenReturn(Optional.of(scenarioDbo));
			when(tokenDboRepository.findById(TOKEN_ID)).thenReturn(Optional.of(tokenDbo));
			// Run
			unitUnderTest.updateTokenPosition(ID, TOKEN_ID, MAP_ID, PARTY_ID, SCENARIO_ID, coordinates);
			// Check
			assertEquals(FIELD_X, dbo.getFieldX());
			assertEquals(FIELD_Y, dbo.getFieldY());
			verify(tokenMapPartyScenarioDboRepository, times(1)).save(dbo);
		}

		@Test
		void callsTheRepositoryMethodCorrectly_forACreate() {
			// Prepare
			BattleMapDbo battleMapDbo = mock(BattleMapDbo.class);
			PartyDbo partyDbo = mock(PartyDbo.class);
			ScenarioDbo scenarioDbo = mock(ScenarioDbo.class);
			TokenDbo tokenDbo = mock(TokenDbo.class);
			TokenMapPartyScenarioDbo dbo = new TokenMapPartyScenarioDbo()
				.setFieldX(FIELD_X)
				.setFieldY(FIELD_Y)
				.setId(ID)
				.setBattleMap(battleMapDbo)
				.setParty(partyDbo)
				.setScenario(scenarioDbo)
				.setToken(tokenDbo);
			when(tokenMapPartyScenarioDboRepository.findById(ID)).thenReturn(Optional.empty());
			when(coordinates.getFieldX()).thenReturn(FIELD_X);
			when(coordinates.getFieldY()).thenReturn(FIELD_Y);
			when(battleMapDboRepository.findById(MAP_ID)).thenReturn(Optional.of(battleMapDbo));
			when(partyDboRepository.findById(PARTY_ID)).thenReturn(Optional.of(partyDbo));
			when(scenarioDboRepository.findById(SCENARIO_ID)).thenReturn(Optional.of(scenarioDbo));
			when(tokenDboRepository.findById(TOKEN_ID)).thenReturn(Optional.of(tokenDbo));
			when(uuidService.create()).thenReturn(ID);
			// Run
			unitUnderTest.updateTokenPosition(ID, TOKEN_ID, MAP_ID, PARTY_ID, SCENARIO_ID, coordinates);
			// Check
			verify(tokenMapPartyScenarioDboRepository, times(1)).save(dbo);
		}
	}
}
