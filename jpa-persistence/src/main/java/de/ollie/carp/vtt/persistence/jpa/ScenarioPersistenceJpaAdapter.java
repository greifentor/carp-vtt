package de.ollie.carp.vtt.persistence.jpa;

import de.ollie.carp.vtt.core.service.model.Scenario;
import de.ollie.carp.vtt.core.service.port.persistence.ScenarioPersistencePort;
import de.ollie.carp.vtt.persistence.jpa.mapper.ScenarioDboMapper;
import de.ollie.carp.vtt.persistence.jpa.repository.ScenarioDboRepository;
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
public class ScenarioPersistenceJpaAdapter implements ScenarioPersistencePort {

	private final ScenarioDboMapper mapper;
	private final ScenarioDboRepository repository;

	@Override
	public Scenario create(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteById(UUID id) {
		// TODO Auto-generated method stub
	}

	@Override
	public Optional<Scenario> findById(UUID id) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public List<Scenario> list() {
		return mapper.toModels(repository.findAll());
	}

	@Override
	public Scenario update(Scenario toSave) {
		return mapper.toModel(repository.save(mapper.toDbo(toSave)));
	}
}
