package de.ollie.carp.vtt.restserver.core.service;

import de.ollie.carp.vtt.restserver.core.service.model.User;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import lombok.Generated;

/**
 * GENERATED CODE - DO NOT TOUCH
 *
 * Remove this comment to suspend class from generation process.
 */
@Generated
public interface UserService {
	User createUser(UUID key, String rights);

	void deleteUser(UUID id);

	Optional<User> findById(UUID id);

	List<User> listUsers();

	String loadSecretByUid(UUID id);

	User updateUser(User toSave);
}
