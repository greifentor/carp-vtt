package de.ollie.carp.vtt.core.service.impl;

import de.ollie.carp.vtt.core.service.BattleMapService;
import de.ollie.carp.vtt.core.service.model.BattleMap;
import de.ollie.carp.vtt.core.service.port.persistence.BattleMapPersistencePort;
import jakarta.inject.Named;
import java.util.List;
import java.util.Optional;
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
class BattleMapServiceImpl implements BattleMapService {

	private final BattleMapPersistencePort battleMapPersistencePort;

	@Override
	public BattleMap createBattleMap(byte[] imageContent, String name) {
		return battleMapPersistencePort.create(imageContent, name);
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
		return battleMapPersistencePort.update(toSave);
	}
}
