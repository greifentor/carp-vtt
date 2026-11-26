package de.ollie.carp.vtt.core.service.port.web;

import de.ollie.carp.vtt.core.service.model.BattleMap;
import java.util.List;
import java.util.UUID;
import lombok.Generated;

/**
 * GENERATED CODE - DO NOT TOUCH
 *
 * Remove this comment to suspend class from generation process.
 */
@Generated
public interface BattleMapWebPort {
	interface SynchronizationObserver {
		void progress(int synced, int total);
	}

	void deleteBattleMap(UUID idToDelete);

	void pushBattleMap(BattleMap toPush);

	void synchronizeBattleMaps(SynchronizationObserver observer, List<BattleMap> battleMaps);
}
