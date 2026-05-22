package de.ollie.carp.vtt.core.service;

import de.ollie.carp.vtt.core.service.model.event.TokenPositionUpdateEvent;

public interface TokenPositionService {
	void updateTokenPosition(TokenPositionUpdateEvent tokenPositionUpdateEvent);
}
