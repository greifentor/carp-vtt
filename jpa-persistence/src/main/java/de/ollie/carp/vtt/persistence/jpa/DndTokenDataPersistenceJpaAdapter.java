package de.ollie.carp.vtt.persistence.jpa;

import de.ollie.carp.vtt.core.service.model.DndTokenData;
import de.ollie.carp.vtt.core.service.port.persistence.DndTokenDataPersistencePort;
import de.ollie.carp.vtt.persistence.jpa.mapper.DndTokenDataDboMapper;
import de.ollie.carp.vtt.persistence.jpa.repository.DndTokenDataDboRepository;
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
public class DndTokenDataPersistenceJpaAdapter implements DndTokenDataPersistencePort {

	private final DndTokenDataDboMapper mapper;
	private final DndTokenDataDboRepository repository;

	@Override
	public DndTokenData create(int hitPoints) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteById(UUID id) {
		repository.deleteById(id);
	}

	@Override
	public Optional<DndTokenData> findById(UUID id) {
		return repository.findById(id).map(mapper::toModel);
	}

	@Override
	public List<DndTokenData> list() {
		return mapper.toModels(repository.findAll());
	}

	@Override
	public DndTokenData update(DndTokenData toSave) {
		return mapper.toModel(repository.save(mapper.toDbo(toSave)));
	}
}
