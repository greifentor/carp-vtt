package de.ollie.carp.vtt.core.service.port.persistence;

import de.ollie.carp.vtt.core.service.model.DndTokenBattleMapPartyScenarioData;
import jakarta.inject.Named;
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
@Named
public interface DndTokenBattleMapPartyScenarioDataPersistencePort {
	DndTokenBattleMapPartyScenarioData create(
		int currentHitPoints,
		int deathSavingThrowsFail,
		int deathSavingThrowsSuccess,
		int temporaryHitPoints
	);

	void deleteById(UUID id);

	Optional<DndTokenBattleMapPartyScenarioData> findById(UUID id);

	List<DndTokenBattleMapPartyScenarioData> list();

	DndTokenBattleMapPartyScenarioData update(DndTokenBattleMapPartyScenarioData toSave);
}
