package de.ollie.carp.vtt.restserver.persistence.jpa;

import de.ollie.carp.vtt.restserver.core.service.model.DndData;
import de.ollie.carp.vtt.restserver.core.service.port.persistence.DndDataPersistencePort;
import de.ollie.carp.vtt.restserver.persistence.jpa.mapper.DndDataDboMapper;
import de.ollie.carp.vtt.restserver.persistence.jpa.repository.DndDataDboRepository;
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
public class DndDataPersistenceJpaAdapter implements DndDataPersistencePort {

	private final DndDataDboMapper mapper;
	private final DndDataDboRepository repository;

	@Override
	public DndData create(int currentHitPoints, int maxHitPoints) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteById(UUID id) {
		repository.deleteById(id);
	}

	@Override
	public Optional<DndData> findById(UUID id) {
		return repository.findById(id).map(mapper::toModel);
	}

	@Override
	public List<DndData> list() {
		return mapper.toModels(repository.findAll());
	}

	@Override
	public DndData update(DndData toSave) {
		return mapper.toModel(repository.save(mapper.toDbo(toSave)));
	}
}
