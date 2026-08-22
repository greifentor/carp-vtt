package de.ollie.carp.vtt.restserver.core.service;

import de.ollie.carp.vtt.restserver.core.service.model.TokenData;
import java.util.List;
import java.util.UUID;

public interface TokenDataService {
	List<TokenData> findAllBy(UUID battleMapId, UUID partyId, UUID scenarioId);
}
