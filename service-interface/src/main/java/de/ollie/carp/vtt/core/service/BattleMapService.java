package de.ollie.carp.vtt.core.service;

import de.ollie.carp.vtt.core.service.model.BattleMap;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import lombok.Generated;

/**
 * GENERATED CODE - DO NOT TOUCH
 *
 * Remove this comment to suspend class from generation process.
 */
@Generated
public interface BattleMapService {
	BattleMap createBattleMap(byte[] imageContent, String name);

	void deleteBattleMap(UUID id);

	Optional<BattleMap> findById(UUID id);

	List<BattleMap> listBattleMaps();

	BattleMap updateBattleMap(BattleMap toSave);
}
