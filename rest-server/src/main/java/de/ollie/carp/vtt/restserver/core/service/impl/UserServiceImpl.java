package de.ollie.carp.vtt.restserver.core.service.impl;

import de.ollie.carp.vtt.restserver.core.service.UserService;
import de.ollie.carp.vtt.restserver.core.service.model.User;
import de.ollie.carp.vtt.restserver.core.service.port.persistence.UserPersistencePort;
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
class UserServiceImpl implements UserService {

	private final UserPersistencePort userPersistencePort;

	@Override
	public User createUser(UUID key, String rights) {
		return userPersistencePort.create(key, rights);
	}

	@Override
	public void deleteUser(UUID id) {
		userPersistencePort.deleteById(id);
	}

	@Override
	public Optional<User> findById(UUID id) {
		return userPersistencePort.findById(id);
	}

	@Override
	public List<User> listUsers() {
		return userPersistencePort.list();
	}

	@Override
	public String loadSecretByUid(UUID id) {
		return findById(id).map(User::getKey).orElse(null);
	}

	@Override
	public User updateUser(User toSave) {
		return userPersistencePort.update(toSave);
	}
}
