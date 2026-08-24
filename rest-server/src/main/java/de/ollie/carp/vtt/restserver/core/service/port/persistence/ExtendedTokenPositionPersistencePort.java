package de.ollie.carp.vtt.restserver.core.service.port.persistence;

import de.ollie.carp.vtt.restserver.core.service.model.TokenPosition;
import java.util.List;
import java.util.UUID;

public interface ExtendedTokenPositionPersistencePort {
	List<TokenPosition> findAllBy(UUID battleMapId, UUID partyId, UUID scenarioId);

	TokenPosition getSelectedToken(UUID battleMapId, UUID partyId, UUID scenarioId);

	void unselect(UUID battleMapId, UUID partyId, UUID scenarioId);
}
