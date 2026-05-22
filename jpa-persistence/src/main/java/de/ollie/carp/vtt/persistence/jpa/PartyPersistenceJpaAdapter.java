package de.ollie.carp.vtt.persistence.jpa;

import de.ollie.carp.vtt.core.service.model.Party;
import de.ollie.carp.vtt.core.service.port.persistence.PartyPersistencePort;
import de.ollie.carp.vtt.persistence.jpa.mapper.PartyDboMapper;
import de.ollie.carp.vtt.persistence.jpa.repository.PartyDboRepository;
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
public class PartyPersistenceJpaAdapter implements PartyPersistencePort {

	private final PartyDboMapper mapper;
	private final PartyDboRepository repository;

	@Override
	public Party create(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteById(UUID id) {
		// TODO Auto-generated method stub
	}

	@Override
	public Optional<Party> findById(UUID id) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public List<Party> list() {
		return mapper.toModels(repository.findAll());
	}

	@Override
	public Party update(Party toSave) {
		return mapper.toModel(repository.save(mapper.toDbo(toSave)));
	}
}
