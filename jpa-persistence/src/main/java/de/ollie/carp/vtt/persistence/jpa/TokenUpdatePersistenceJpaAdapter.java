package de.ollie.carp.vtt.persistence.jpa;

import static de.ollie.baselib.util.Check.ensure;

import de.ollie.carp.vtt.core.service.UuidService;
import de.ollie.carp.vtt.core.service.model.Coordinates;
import de.ollie.carp.vtt.core.service.port.persistence.TokenUpdatePersistencePort;
import de.ollie.carp.vtt.persistence.jpa.dbo.MapDbo;
import de.ollie.carp.vtt.persistence.jpa.dbo.PartyDbo;
import de.ollie.carp.vtt.persistence.jpa.dbo.ScenarioDbo;
import de.ollie.carp.vtt.persistence.jpa.dbo.TokenDbo;
import de.ollie.carp.vtt.persistence.jpa.dbo.TokenMapPartyScenarioDbo;
import de.ollie.carp.vtt.persistence.jpa.repository.MapDboRepository;
import de.ollie.carp.vtt.persistence.jpa.repository.PartyDboRepository;
import de.ollie.carp.vtt.persistence.jpa.repository.ScenarioDboRepository;
import de.ollie.carp.vtt.persistence.jpa.repository.TokenDboRepository;
import de.ollie.carp.vtt.persistence.jpa.repository.TokenMapPartyScenarioDboRepository;
import jakarta.inject.Named;
import java.util.NoSuchElementException;
import java.util.UUID;
import lombok.RequiredArgsConstructor;

@Named
@RequiredArgsConstructor
public class TokenUpdatePersistenceJpaAdapter implements TokenUpdatePersistencePort {

	private final MapDboRepository mapDboRepository;
	private final PartyDboRepository partyDboRepository;
	private final ScenarioDboRepository scenarioDboRepository;
	private final TokenDboRepository tokenDboRepository;
	private final TokenMapPartyScenarioDboRepository tokenMapPartyScenarioDboRepository;

	private final UuidService uuidService;

	@Override
	public void updateTokenPosition(UUID tokenId, UUID mapId, UUID partyId, UUID scenarioId, Coordinates coordinates) {
		ensure(coordinates != null, "coordinates can not be null!");
		ensure(mapId != null, "map id can not be null!");
		ensure(partyId != null, "party id can not be null!");
		ensure(scenarioId != null, "scenario id can not be null!");
		ensure(tokenId != null, "token id can not be null!");
		MapDbo mapDbo = mapDboRepository
			.findById(mapId)
			.orElseThrow(() -> new NoSuchElementException("Map with id not found: " + mapId));
		PartyDbo partyDbo = partyDboRepository
			.findById(partyId)
			.orElseThrow(() -> new NoSuchElementException("Party with id not found: " + partyId));
		ScenarioDbo scenarioDbo = scenarioDboRepository
			.findById(scenarioId)
			.orElseThrow(() -> new NoSuchElementException("Scenario with id not found: " + scenarioId));
		TokenDbo tokenDbo = tokenDboRepository
			.findById(tokenId)
			.orElseThrow(() -> new NoSuchElementException("Token with id not found: " + tokenId));
		tokenMapPartyScenarioDboRepository
			.findByTokenMapPartyScenario(tokenDbo, mapDbo, partyDbo, scenarioDbo)
			.ifPresentOrElse(
				tmps -> {
					tmps.setFieldX(coordinates.getFieldX()).setFieldY(coordinates.getFieldY());
					tokenMapPartyScenarioDboRepository.save(tmps);
				},
				() -> {
					TokenMapPartyScenarioDbo tmps = new TokenMapPartyScenarioDbo()
						.setFieldX(coordinates.getFieldX())
						.setFieldY(coordinates.getFieldY())
						.setId(uuidService.create())
						.setMap(mapDbo)
						.setParty(partyDbo)
						.setScenario(scenarioDbo)
						.setToken(tokenDbo);
					tokenMapPartyScenarioDboRepository.save(tmps);
				}
			);
	}
}
