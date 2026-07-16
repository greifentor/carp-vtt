package de.ollie.carp.vtt.core.service.port.outbox;

import de.ollie.carp.vtt.core.service.model.BattleMap;

/**
 * Outbound port that is notified whenever a {@link BattleMap} has been handed over to the persistence layer for saving.
 */
public interface BattleMapOutboxPort {
	/**
	 * Called after a battle map has been passed to the persistence port for saving.
	 *
	 * @param battleMap the battle map which has been saved (including the values assigned during persistence, e.g. the
	 *                  id).
	 */
	void battleMapSaved(BattleMap battleMap);
}
