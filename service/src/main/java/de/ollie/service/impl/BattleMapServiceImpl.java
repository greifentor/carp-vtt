package de.ollie.service.impl;

import static de.ollie.baselib.util.Check.ensure;

import de.ollie.service.BattleMapService;
import de.ollie.service.model.BattleMap;
import de.ollie.service.model.BattleMapId;
import de.ollie.service.port.persistence.BattleMapPersistencePort;
import jakarta.inject.Named;
import java.util.Optional;
import lombok.RequiredArgsConstructor;

@Named
@RequiredArgsConstructor
class BattleMapServiceImpl implements BattleMapService {

	private final BattleMapPersistencePort persistencePort;

	@Override
	public Optional<BattleMap> findById(BattleMapId id) {
		ensure(id != null, "id cannot be null!");
		return persistencePort.findById(id);
	}
}
