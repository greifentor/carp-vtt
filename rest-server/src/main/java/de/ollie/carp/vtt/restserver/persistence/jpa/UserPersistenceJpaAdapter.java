package de.ollie.carp.vtt.restserver.persistence.jpa;

import de.ollie.carp.vtt.restserver.core.service.model.User;
import de.ollie.carp.vtt.restserver.core.service.port.persistence.UserPersistencePort;
import de.ollie.carp.vtt.restserver.persistence.jpa.mapper.UserDboMapper;
import de.ollie.carp.vtt.restserver.persistence.jpa.repository.UserDboRepository;
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
public class UserPersistenceJpaAdapter implements UserPersistencePort {

	private final UserDboMapper mapper;
	private final UserDboRepository repository;

	@Override
	public User create(UUID key, String rights) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteById(UUID id) {
		repository.deleteById(id);
	}

	@Override
	public Optional<User> findById(UUID id) {
		return repository.findById(id).map(mapper::toModel);
	}

	@Override
	public List<User> list() {
		return mapper.toModels(repository.findAll());
	}

	@Override
	public User update(User toSave) {
		return mapper.toModel(repository.save(mapper.toDbo(toSave)));
	}
}
