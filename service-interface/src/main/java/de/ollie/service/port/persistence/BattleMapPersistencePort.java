package de.ollie.service.port.persistence;

import de.ollie.service.model.BattleMap;
import de.ollie.service.model.BattleMapId;
import java.util.Optional;

public interface BattleMapPersistencePort {
	Optional<BattleMap> findById(BattleMapId id);
}
