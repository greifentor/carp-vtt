package de.ollie.carp.vtt.core.service.port.persistence;

import de.ollie.carp.vtt.core.service.model.CoordinatesInfoProvider;
import de.ollie.carp.vtt.core.service.model.TokenMapData;
import de.ollie.carp.vtt.core.service.model.TokenMapPartyScenario;
import java.util.List;
import java.util.UUID;

public interface TokenUpdatePersistencePort {
	void deleteTokenPosition(UUID id);

	List<TokenMapPartyScenario> findAll();

	List<TokenMapData> findAllByMapPartyScenario(UUID mapId, UUID partyId, UUID scenarioId);

	UUID updateTokenPosition(
		UUID id,
		UUID tokenId,
		UUID mapId,
		UUID partyId,
		UUID scenarioId,
		CoordinatesInfoProvider coordinates,
		int counter
	);
}
