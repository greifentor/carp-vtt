package de.ollie.carp.vtt.core.service.impl;

import de.ollie.carp.vtt.core.service.BattleMapService;
import de.ollie.carp.vtt.core.service.model.BattleMap;
import de.ollie.carp.vtt.core.service.port.persistence.BattleMapPersistencePort;
import de.ollie.carp.vtt.core.service.port.web.BattleMapWebPort;
import jakarta.inject.Named;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import lombok.Generated;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

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
	private final BattleMapWebPort battleMapWebPort;

	@Override
	public BattleMap createBattleMap(byte[] imageContent, String name, UUID owner) {
		return battleMapPersistencePort.create(imageContent, name, owner);
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

	@Override
	public void pushBattleMap(BattleMap toPush) {
		battleMapWebPort.pushBattleMap(toPush);
	}

	@Override
	public void pushDeleteBattleMap(UUID idToDelete) {
		battleMapWebPort.deleteBattleMap(idToDelete);
	}

	@Override
	@Transactional
	public void updateAndPushBattleMap(BattleMap toSave) {
		battleMapWebPort.pushBattleMap(toSave);
		battleMapPersistencePort.update(toSave);
	}

	@Override
	@Transactional
	public void deleteAndPushDeleteBattleMap(UUID idToDelete) {
		battleMapWebPort.deleteBattleMap(idToDelete);
		battleMapPersistencePort.deleteById(idToDelete);
	}
}
