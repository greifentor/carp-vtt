package de.ollie.carp.vtt.core.service;

import de.ollie.carp.vtt.core.service.model.DndTokenMapData;
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
public interface DndTokenMapDataService {
	DndTokenMapData createDndTokenMapData(
		int currentHitPoints,
		int deathSavingThrowsFail,
		int deathSavingThrowsSuccess,
		int temporaryHitPoints
	);

	void deleteDndTokenMapData(UUID id);

	Optional<DndTokenMapData> findById(UUID id);

	List<DndTokenMapData> listDndTokenMapDatas();

	DndTokenMapData updateDndTokenMapData(DndTokenMapData toSave);
}
