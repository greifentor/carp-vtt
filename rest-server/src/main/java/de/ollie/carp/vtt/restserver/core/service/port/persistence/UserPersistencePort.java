package de.ollie.carp.vtt.restserver.core.service.port.persistence;

import de.ollie.carp.vtt.restserver.core.service.model.User;
import jakarta.inject.Named;
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
@Named
public interface UserPersistencePort {
	User create(UUID key, String rights);

	void deleteById(UUID id);

	Optional<User> findById(UUID id);

	List<User> list();

	User update(User toSave);
}
