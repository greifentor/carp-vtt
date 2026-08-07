package de.ollie.carp.vtt.core.service.port.web;

import de.ollie.carp.vtt.core.service.model.event.BattleMapUpdateEvent;

public interface BattleMapWebPort {
	void pushBattleMapUpdate(BattleMapUpdateEvent battleMapUpdateEvent);
}
