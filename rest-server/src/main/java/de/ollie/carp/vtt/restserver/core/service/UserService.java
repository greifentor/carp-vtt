package de.ollie.carp.vtt.restserver.core.service;

import de.ollie.carp.vtt.restserver.core.service.model.User;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserService {
	Optional<User> findById(UUID id);

	List<User> listUsers();

	String loadSecretByUid(UUID id);
}
