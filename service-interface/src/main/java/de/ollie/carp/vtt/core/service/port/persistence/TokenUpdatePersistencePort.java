package de.ollie.carp.vtt.core.service.port.persistence;

import de.ollie.carp.vtt.core.service.model.Coordinates;
import java.util.UUID;

public interface TokenUpdatePersistencePort {
	void updateTokenPosition(UUID tokenId, UUID mapId, UUID partyId, UUID scenarioId, Coordinates coordinates);
}
