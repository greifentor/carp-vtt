package de.ollie.carp.vtt.core.service.impl;

import de.ollie.carp.vtt.core.service.DndTokenMapDataService;
import de.ollie.carp.vtt.core.service.model.DndTokenMapData;
import de.ollie.carp.vtt.core.service.port.persistence.DndTokenMapDataPersistencePort;
import jakarta.inject.Named;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import lombok.Generated;
import lombok.RequiredArgsConstructor;

/**
 * GENERATED CODE - DO NOT TOUCH
 *
 * Remove this comment to suspend class from generation process.
 */
@Generated
@Named
@RequiredArgsConstructor
class DndTokenMapDataServiceImpl implements DndTokenMapDataService {

	private final DndTokenMapDataPersistencePort dndTokenMapDataPersistencePort;

	@Override
	public DndTokenMapData createDndTokenMapData(
		int currentHitPoints,
		int deathSavingThrowsFail,
		int deathSavingThrowsSuccess,
		int temporaryHitPoints
	) {
		return dndTokenMapDataPersistencePort.create(
			currentHitPoints,
			deathSavingThrowsFail,
			deathSavingThrowsSuccess,
			temporaryHitPoints
		);
	}

	@Override
	public void deleteDndTokenMapData(UUID id) {
		dndTokenMapDataPersistencePort.deleteById(id);
	}

	@Override
	public Optional<DndTokenMapData> findById(UUID id) {
		return dndTokenMapDataPersistencePort.findById(id);
	}

	@Override
	public List<DndTokenMapData> listDndTokenMapDatas() {
		return dndTokenMapDataPersistencePort.list();
	}

	@Override
	public DndTokenMapData updateDndTokenMapData(DndTokenMapData toSave) {
		return dndTokenMapDataPersistencePort.update(toSave);
	}
}
