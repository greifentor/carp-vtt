package de.ollie.carp.vtt.core.service.port.web;

import de.ollie.carp.vtt.core.service.model.event.TokenPositionUpdateEvent;

public interface TokenWebPort {
	void pushTokenPositionUpdate(TokenPositionUpdateEvent tokenPositionUpdateEvent);
}
