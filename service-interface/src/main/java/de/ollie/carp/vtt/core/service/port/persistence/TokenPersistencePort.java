package de.ollie.carp.vtt.core.service.port.persistence;

import de.ollie.carp.vtt.core.service.model.Token;
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
public interface TokenPersistencePort {
	Token create(String name, byte[] image);

	void deleteById(UUID id);

	Optional<Token> findById(UUID id);

	List<Token> list();

	Token update(Token toSave);
}
