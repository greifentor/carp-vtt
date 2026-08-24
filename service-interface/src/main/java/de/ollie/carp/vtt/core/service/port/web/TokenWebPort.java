package de.ollie.carp.vtt.core.service.port.web;

import de.ollie.carp.vtt.core.service.model.event.TokenPositionUpdateEvent;
import de.ollie.carp.vtt.core.service.model.event.TokenUpdateEvent;
import java.util.UUID;

public interface TokenWebPort {
	interface SynchronizationObserver {
		void progress(int synced, int total);
	}

	void pushTokenPositionUpdate(TokenPositionUpdateEvent tokenPositionUpdateEvent);

	void pushTokenUpdate(TokenUpdateEvent tokenUpdateEvent);

	void synchronizeTokens(SynchronizationObserver observer);

	void synchronizeTokenUpdates(SynchronizationObserver observer);

	void unselect(UUID battleMapId, UUID partyId, UUID scenarioId);
}
