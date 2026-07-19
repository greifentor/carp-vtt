package de.ollie.carp.vtt.core.service.port.persistence;

import de.ollie.carp.vtt.core.service.model.Coordinates;
import de.ollie.carp.vtt.core.service.model.TokenData;
import java.util.List;
import java.util.UUID;

public interface TokenUpdatePersistencePort {
	List<TokenData> findAllByMapPartyScenario(UUID mapId, UUID partyId, UUID scenarioId);

	UUID updateTokenPosition(UUID id, UUID tokenId, UUID mapId, UUID partyId, UUID scenarioId, Coordinates coordinates);
}
