package de.ollie.carp.vtt.restserver.core.service.impl;

import de.ollie.carp.vtt.restserver.core.service.BattleMapService;
import de.ollie.carp.vtt.restserver.core.service.model.BattleMap;
import de.ollie.carp.vtt.restserver.core.service.port.outbox.BattleMapOutboxPort;
import de.ollie.carp.vtt.restserver.core.service.port.persistence.BattleMapPersistencePort;
import jakarta.inject.Named;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;

@Named
@RequiredArgsConstructor
class BattleMapServiceImpl implements BattleMapService {

	private final BattleMapPersistencePort battleMapPersistencePort;
	private final BattleMapOutboxPort battleMapOutboxPort;

	@Override
	public BattleMap createBattleMap(String name, byte[] image) {
		BattleMap saved = battleMapPersistencePort.create(name, image);
		battleMapOutboxPort.battleMapSaved(saved);
		return saved;
	}

	@Override
	public void deleteBattleMap(UUID id) {
		battleMapPersistencePort.deleteById(id);
	}

	@Override
	public Optional<BattleMap> findById(UUID id) {
		return battleMapPersistencePort.findById(id);
	}

	@Override
	public List<BattleMap> listBattleMaps() {
		return battleMapPersistencePort.list();
	}

	@Override
	public BattleMap updateBattleMap(BattleMap toSave) {
		BattleMap saved = battleMapPersistencePort.update(toSave);
		battleMapOutboxPort.battleMapSaved(saved);
		return saved;
	}
}
