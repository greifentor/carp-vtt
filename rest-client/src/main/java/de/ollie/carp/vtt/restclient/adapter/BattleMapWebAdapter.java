package de.ollie.carp.vtt.restclient.adapter;

import de.ollie.carp.vtt.core.service.model.event.BattleMapUpdateEvent;
import de.ollie.carp.vtt.core.service.port.web.BattleMapWebPort;
import de.ollie.carp.vtt.restclient.BattleMapClient;
import jakarta.inject.Named;
import lombok.RequiredArgsConstructor;

@Named
@RequiredArgsConstructor
public class BattleMapWebAdapter implements BattleMapWebPort {

	private final BattleMapClient battleMapClient;

	@Override
	public void pushBattleMapUpdate(BattleMapUpdateEvent battleMapUpdateEvent) {
		battleMapClient.updateBattleMap(battleMapUpdateEvent);
	}
}
