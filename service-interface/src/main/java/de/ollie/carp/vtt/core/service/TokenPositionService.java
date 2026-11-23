package de.ollie.carp.vtt.core.service;

import de.ollie.carp.vtt.core.service.model.TokenMapPartyScenario;
import de.ollie.carp.vtt.core.service.model.event.TokenPositionUpdateEvent;
import java.util.List;
import java.util.UUID;

public interface TokenPositionService {
	void delete(UUID tokenPositionId);

	List<TokenMapPartyScenario> findAll();

	void updateTokenPosition(TokenPositionUpdateEvent tokenPositionUpdateEvent);
}
