package de.ollie.carp.vtt.restserver.core.service;

import de.ollie.carp.vtt.restserver.core.service.model.DndData;
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
public interface DndDataService {
	DndData createDndData(int currentHitPoints, int maxHitPoints);

	void deleteDndData(UUID id);

	Optional<DndData> findById(UUID id);

	List<DndData> listDndDatas();

	DndData updateDndData(DndData toSave);
}
