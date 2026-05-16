package de.ollie.carp.vtt.core.service.port.persistence;

import de.ollie.carp.vtt.core.service.model.TokenTyp;
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
public interface TokenTypPersistencePort {
	TokenTyp create(String name, byte[] token);

	void deleteById(UUID id);

	Optional<TokenTyp> findById(UUID id);

	Optional<TokenTyp> findByIdOrNameParticle(String name);

	List<TokenTyp> list();

	TokenTyp update(TokenTyp toSave);
}
