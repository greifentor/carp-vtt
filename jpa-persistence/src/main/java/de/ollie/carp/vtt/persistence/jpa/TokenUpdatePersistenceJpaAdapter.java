package de.ollie.carp.vtt.persistence.jpa;

import static de.ollie.baselib.util.Check.ensure;

import de.ollie.carp.vtt.core.service.model.Coordinates;
import de.ollie.carp.vtt.core.service.port.persistence.TokenUpdatePersistencePort;
import de.ollie.carp.vtt.persistence.jpa.repository.TokenMapPartyScenarioDboRepository;
import jakarta.inject.Named;
import java.util.UUID;
import lombok.RequiredArgsConstructor;

@Named
@RequiredArgsConstructor
public class TokenUpdatePersistenceJpaAdapter implements TokenUpdatePersistencePort {

	private final TokenMapPartyScenarioDboRepository tokenMapPartyScenarioDboRepository;

	@Override
	public void updateTokenPosition(UUID tokenId, UUID mapId, UUID partyId, UUID scenarioId, Coordinates coordinates) {
		ensure(coordinates != null, "coordinates can not be null!");
		ensure(mapId != null, "map id can not be null!");
		ensure(partyId != null, "party id can not be null!");
		ensure(scenarioId != null, "scenario id can not be null!");
		ensure(tokenId != null, "token id can not be null!");
	}
}
