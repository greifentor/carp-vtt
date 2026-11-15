package de.ollie.carp.vtt.restserver.core.service.impl;

import de.ollie.carp.vtt.restserver.core.service.UserService;
import de.ollie.carp.vtt.restserver.core.service.model.User;
import de.ollie.carp.vtt.restserver.core.service.port.persistence.UserPersistencePort;
import jakarta.inject.Named;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;

@Named
@RequiredArgsConstructor
class UserServiceImpl implements UserService {

	private final UserPersistencePort userPersistencePort;

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
		return findById(id).map(u -> u.getKey().toString()).orElse(null);
	}
}
