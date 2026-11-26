package de.ollie.carp.vtt.restclient.adapter;

import de.ollie.carp.vtt.core.service.model.BattleMap;
import de.ollie.carp.vtt.core.service.port.web.BattleMapWebPort;
import de.ollie.carp.vtt.restclient.BattleMapClient;
import jakarta.inject.Named;
import java.util.List;
import java.util.UUID;
import lombok.Generated;
import lombok.RequiredArgsConstructor;

/**
 * GENERATED CODE - DO NOT TOUCH
 *
 * Remove this comment to suspend class from generation process.
 */
@Generated
@Named
@RequiredArgsConstructor
public class BattleMapWebAdapter implements BattleMapWebPort {

	private final BattleMapClient client;

	@Override
	public void pushBattleMap(BattleMap toPush) {
		client.updateBattleMap(toPush);
	}

	@Override
	public void deleteBattleMap(UUID idToDelete) {
		client.removeBattleMap(idToDelete);
	}

	@Override
	public void synchronizeBattleMaps(SynchronizationObserver observer, List<BattleMap> battleMaps) {
		for (int i = 0, leni = battleMaps.size(); i < leni; i++) {
			client.updateBattleMap(battleMaps.get(i));
			if (observer != null) {
				observer.progress(i, leni - 1);
			}
		}
	}
}
