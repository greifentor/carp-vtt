package de.ollie.carp.vtt.persistence.jpa;

import de.ollie.carp.vtt.core.service.model.DndTokenMapData;
import de.ollie.carp.vtt.core.service.port.persistence.DndTokenMapDataPersistencePort;
import de.ollie.carp.vtt.persistence.jpa.mapper.DndTokenMapDataDboMapper;
import de.ollie.carp.vtt.persistence.jpa.repository.DndTokenMapDataDboRepository;
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
public class DndTokenMapDataPersistenceJpaAdapter implements DndTokenMapDataPersistencePort {

	private final DndTokenMapDataDboMapper mapper;
	private final DndTokenMapDataDboRepository repository;

	@Override
	public DndTokenMapData create(
		int currentHitPoints,
		int deathSavingThrowsFail,
		int deathSavingThrowsSuccess,
		int temporaryHitPoints
	) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteById(UUID id) {
		repository.deleteById(id);
	}

	@Override
	public Optional<DndTokenMapData> findById(UUID id) {
		return repository.findById(id).map(mapper::toModel);
	}

	@Override
	public List<DndTokenMapData> list() {
		return mapper.toModels(repository.findAll());
	}

	@Override
	public DndTokenMapData update(DndTokenMapData toSave) {
		return mapper.toModel(repository.save(mapper.toDbo(toSave)));
	}
}
