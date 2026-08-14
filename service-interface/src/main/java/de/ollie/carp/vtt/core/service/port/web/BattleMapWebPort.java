package de.ollie.carp.vtt.core.service.port.web;

import de.ollie.carp.vtt.core.service.model.event.BattleMapUpdateEvent;

public interface BattleMapWebPort {
	interface SynchronizationObserver {
		void progress(int synced, int total);
	}

	void pushBattleMapUpdate(BattleMapUpdateEvent battleMapUpdateEvent);

	void synchronizeBattleMaps(SynchronizationObserver observer);
}
