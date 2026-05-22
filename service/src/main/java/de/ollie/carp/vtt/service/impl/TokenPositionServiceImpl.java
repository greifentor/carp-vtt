package de.ollie.carp.vtt.service.impl;

import static de.ollie.baselib.util.Check.ensure;

import de.ollie.carp.vtt.core.service.TokenPositionService;
import de.ollie.carp.vtt.core.service.model.event.TokenPositionUpdateEvent;
import de.ollie.carp.vtt.core.service.port.persistence.TokenUpdatePersistencePort;
import jakarta.inject.Named;
import lombok.RequiredArgsConstructor;

@Named
@RequiredArgsConstructor
class TokenPositionServiceImpl implements TokenPositionService {

	private final TokenUpdatePersistencePort tokenUpdatePort;

	@Override
	public void updateTokenPosition(TokenPositionUpdateEvent tokenPositionUpdateEvent) {
		ensure(tokenPositionUpdateEvent != null, "token position update event cannot be null!");
		tokenUpdatePort.updateTokenPosition(
			tokenPositionUpdateEvent.getTokenId(),
			tokenPositionUpdateEvent.getMapId(),
			tokenPositionUpdateEvent.getPartyId(),
			tokenPositionUpdateEvent.getScenarioId(),
			tokenPositionUpdateEvent.coordinates()
		);
	}
}
