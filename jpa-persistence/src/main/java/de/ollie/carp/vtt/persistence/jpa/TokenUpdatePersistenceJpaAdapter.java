package de.ollie.carp.vtt.persistence.jpa;

import static de.ollie.baselib.util.Check.ensure;

import de.ollie.carp.vtt.core.service.UuidService;
import de.ollie.carp.vtt.core.service.model.Coordinates;
import de.ollie.carp.vtt.core.service.model.TokenData;
import de.ollie.carp.vtt.core.service.port.persistence.TokenUpdatePersistencePort;
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
import jakarta.inject.Named;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;
import lombok.RequiredArgsConstructor;

@Named
@RequiredArgsConstructor
public class TokenUpdatePersistenceJpaAdapter implements TokenUpdatePersistencePort {

	private final BattleMapDboRepository battleMapDboRepository;
	private final PartyDboRepository partyDboRepository;
	private final ScenarioDboRepository scenarioDboRepository;
	private final TokenDboRepository tokenDboRepository;
	private final TokenDboMapper tokenDboMapper;
	private final TokenMapPartyScenarioDboRepository tokenMapPartyScenarioDboRepository;

	private final UuidService uuidService;

	@Override
	public List<TokenData> findAllByMapPartyScenario(UUID battleMapId, UUID partyId, UUID scenarioId) {
		ensure(battleMapId != null, "battle map id cannot be null!");
		ensure(partyId != null, "party id cannot be null!");
		ensure(scenarioId != null, "scenario id cannot be null!");
		BattleMapDbo mapDbo = battleMapDboRepository
			.findById(battleMapId)
			.orElseThrow(() -> new NoSuchElementException("Map with id not found: " + battleMapId));
		PartyDbo partyDbo = partyDboRepository
			.findById(partyId)
			.orElseThrow(() -> new NoSuchElementException("Party with id not found: " + partyId));
		ScenarioDbo scenarioDbo = scenarioDboRepository
			.findById(scenarioId)
			.orElseThrow(() -> new NoSuchElementException("Scenario with id not found: " + scenarioId));
		List<TokenMapPartyScenarioDbo> dbos = tokenMapPartyScenarioDboRepository.findAllByAndBattleMapAndPartyAndScenario(
			mapDbo,
			partyDbo,
			scenarioDbo
		);
		return map(dbos);
	}

	private List<TokenData> map(List<TokenMapPartyScenarioDbo> dbos) {
		return dbos
			.stream()
			.map(dbo ->
				new TokenData()
					.setCoordinates(new Coordinates().setFieldX(dbo.getFieldX()).setFieldY(dbo.getFieldY()))
					.setId(dbo.getId())
					.setToken(tokenDboMapper.toModel(dbo.getToken()))
			)
			.toList();
	}

	@Override
	public UUID updateTokenPosition(
		UUID id,
		UUID tokenId,
		UUID battleMapId,
		UUID partyId,
		UUID scenarioId,
		Coordinates coordinates
	) {
		ensure(coordinates != null, "coordinates can not be null!");
		ensure(id != null, "id can not be null!");
		ensure(battleMapId != null, "battle map id can not be null!");
		ensure(partyId != null, "party id can not be null!");
		ensure(scenarioId != null, "scenario id can not be null!");
		ensure(tokenId != null, "token id can not be null!");
		BattleMapDbo battleMapDbo = battleMapDboRepository
			.findById(battleMapId)
			.orElseThrow(() -> new NoSuchElementException("Battle Map with id not found: " + battleMapId));
		PartyDbo partyDbo = partyDboRepository
			.findById(partyId)
			.orElseThrow(() -> new NoSuchElementException("Party with id not found: " + partyId));
		ScenarioDbo scenarioDbo = scenarioDboRepository
			.findById(scenarioId)
			.orElseThrow(() -> new NoSuchElementException("Scenario with id not found: " + scenarioId));
		TokenDbo tokenDbo = tokenDboRepository
			.findById(tokenId)
			.orElseThrow(() -> new NoSuchElementException("Token with id not found: " + tokenId));
		System.out.println("id -   " + id);
		return tokenMapPartyScenarioDboRepository
			.findById(id)
			.map(tmps -> {
				System.out.println("read - " + tmps.getId());
				tmps.setFieldX(coordinates.getFieldX()).setFieldY(coordinates.getFieldY());
				tokenMapPartyScenarioDboRepository.save(tmps);
				return id;
			})
			.orElseGet(() -> {
				TokenMapPartyScenarioDbo tmps = new TokenMapPartyScenarioDbo()
					.setId(id)
					.setFieldX(coordinates.getFieldX())
					.setFieldY(coordinates.getFieldY())
					.setBattleMap(battleMapDbo)
					.setParty(partyDbo)
					.setScenario(scenarioDbo)
					.setToken(tokenDbo);
				System.out.println("new -  " + tmps.getId());
				tokenMapPartyScenarioDboRepository.save(tmps);
				return id;
			});
	}
}
