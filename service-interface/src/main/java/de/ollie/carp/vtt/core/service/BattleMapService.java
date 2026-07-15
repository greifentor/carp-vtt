package de.ollie.carp.vtt.core.service;

import de.ollie.carp.vtt.core.service.model.BattleMap;
import java.util.List;

public interface BattleMapService {
	List<BattleMap> findAll();

	BattleMap save(BattleMap toSave);
}
