package de.ollie.service.impl;

import static de.ollie.baselib.util.Check.ensure;

import de.ollie.vtt.core.service.BattleMapService;
import de.ollie.vtt.core.service.model.BattleMap;
import de.ollie.vtt.core.service.port.persistence.BattleMapPersistencePort;
import jakarta.inject.Named;
import lombok.RequiredArgsConstructor;

@Named
@RequiredArgsConstructor
class BattleMapServiceImpl implements BattleMapService {

	private final BattleMapPersistencePort persistencePort;

	@Override
	public BattleMap save(BattleMap toSave) {
		ensure(toSave != null, "object to save cannot be null!");
		return persistencePort.update(toSave);
	}
}
