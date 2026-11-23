package de.ollie.carp.vtt.core.service.port.persistence;

import de.ollie.carp.vtt.core.service.model.DndTokenMapData;
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
public interface DndTokenMapDataPersistencePort {
	DndTokenMapData create(
		int currentHitPoints,
		int deathSavingThrowsFail,
		int deathSavingThrowsSuccess,
		int temporaryHitPoints
	);

	void deleteById(UUID id);

	Optional<DndTokenMapData> findById(UUID id);

	List<DndTokenMapData> list();

	DndTokenMapData update(DndTokenMapData toSave);
}
