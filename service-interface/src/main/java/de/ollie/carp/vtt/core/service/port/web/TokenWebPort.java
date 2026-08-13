package de.ollie.carp.vtt.core.service.port.web;

import de.ollie.carp.vtt.core.service.model.event.TokenPositionUpdateEvent;
import de.ollie.carp.vtt.core.service.model.event.TokenUpdateEvent;

public interface TokenWebPort {
	interface SynchronizationObserver {
		void progress(int synced, int total);
	}

	void pushTokenPositionUpdate(TokenPositionUpdateEvent tokenPositionUpdateEvent);

	void pushTokenUpdate(TokenUpdateEvent tokenUpdateEvent);

	void synchronize(SynchronizationObserver observer);
}
