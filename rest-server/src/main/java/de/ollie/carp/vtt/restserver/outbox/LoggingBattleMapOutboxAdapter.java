package de.ollie.carp.vtt.restserver.outbox;

import de.ollie.carp.vtt.restserver.core.service.model.BattleMap;
import de.ollie.carp.vtt.restserver.core.service.port.outbox.BattleMapOutboxPort;
import jakarta.inject.Named;
import lombok.extern.slf4j.Slf4j;

/**
 * Default {@link BattleMapOutboxPort} implementation which logs that a battle map has been saved.
 *
 * <p>Serves as a starting point for a real outbox implementation (e.g. publishing an event or writing an outbox table
 * record).
 */
@Slf4j
@Named
public class LoggingBattleMapOutboxAdapter implements BattleMapOutboxPort {

	@Override
	public void battleMapSaved(BattleMap battleMap) {
		log.info("battle map saved: id={}, name={}", battleMap.getId(), battleMap.getName());
	}
}
