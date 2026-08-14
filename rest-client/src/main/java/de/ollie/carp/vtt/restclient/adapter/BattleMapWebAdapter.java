package de.ollie.carp.vtt.restclient.adapter;

import de.ollie.carp.vtt.core.service.BattleMapService;
import de.ollie.carp.vtt.core.service.model.BattleMap;
import de.ollie.carp.vtt.core.service.model.event.BattleMapUpdateEvent;
import de.ollie.carp.vtt.core.service.port.web.BattleMapWebPort;
import de.ollie.carp.vtt.restclient.BattleMapClient;
import jakarta.inject.Named;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;

@Named
@RequiredArgsConstructor
public class BattleMapWebAdapter implements BattleMapWebPort {

	private final BattleMapClient battleMapClient;
	private final BattleMapService battleMapService;

	@Override
	public void pushBattleMapUpdate(BattleMapUpdateEvent battleMapUpdateEvent) {
		battleMapClient.updateBattleMap(battleMapUpdateEvent);
	}

	@Override
	public void synchronizeBattleMaps(SynchronizationObserver observer) {
		List<BattleMap> battleMaps = battleMapService.findAll();
		for (int i = 0, leni = battleMaps.size(); i < leni; i++) {
			pushBattleMapUpdate(new BattleMapUpdateEvent(UUID.randomUUID(), battleMaps.get(i)));
			if (observer != null) {
				observer.progress(i, leni - 1);
			}
		}
	}
}
