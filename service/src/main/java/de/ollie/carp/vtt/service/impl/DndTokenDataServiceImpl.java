package de.ollie.carp.vtt.service.impl;

import de.ollie.carp.vtt.core.service.DndTokenDataService;
import de.ollie.carp.vtt.core.service.model.DndTokenData;
import de.ollie.carp.vtt.core.service.port.persistence.DndTokenDataPersistencePort;
import jakarta.inject.Named;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;

@Named
@RequiredArgsConstructor
class DndTokenDataServiceImpl implements DndTokenDataService {

	private final DndTokenDataPersistencePort dndTokenDataPersistencePort;

	@Override
	public DndTokenData create(int hitPoints) {
		return dndTokenDataPersistencePort.create(hitPoints);
	}

	@Override
	public Optional<DndTokenData> findById(UUID id) {
		return dndTokenDataPersistencePort.findById(id);
	}

	@Override
	public DndTokenData save(DndTokenData toSave) {
		return dndTokenDataPersistencePort.update(toSave);
	}
}
