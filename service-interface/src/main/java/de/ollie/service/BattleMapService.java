package de.ollie.service;

import de.ollie.service.model.BattleMap;
import de.ollie.service.model.BattleMapId;
import java.util.Optional;

public interface BattleMapService {
	Optional<BattleMap> findById(BattleMapId id);
}
