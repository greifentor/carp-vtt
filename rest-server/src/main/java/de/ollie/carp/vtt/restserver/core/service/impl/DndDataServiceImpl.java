package de.ollie.carp.vtt.restserver.core.service.impl;

import de.ollie.carp.vtt.restserver.core.service.DndDataService;
import de.ollie.carp.vtt.restserver.core.service.model.DndData;
import de.ollie.carp.vtt.restserver.core.service.port.persistence.DndDataPersistencePort;
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
class DndDataServiceImpl implements DndDataService {

	private final DndDataPersistencePort dndDataPersistencePort;

	@Override
	public DndData createDndData(int currentHitPoints, int maxHitPoints) {
		return dndDataPersistencePort.create(currentHitPoints, maxHitPoints);
	}

	@Override
	public void deleteDndData(UUID id) {
		dndDataPersistencePort.deleteById(id);
	}

	@Override
	public Optional<DndData> findById(UUID id) {
		return dndDataPersistencePort.findById(id);
	}

	@Override
	public List<DndData> listDndDatas() {
		return dndDataPersistencePort.list();
	}

	@Override
	public DndData updateDndData(DndData toSave) {
		return dndDataPersistencePort.update(toSave);
	}
}
