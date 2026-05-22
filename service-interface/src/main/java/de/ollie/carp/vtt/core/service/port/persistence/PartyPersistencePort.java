package de.ollie.carp.vtt.core.service.port.persistence;

import de.ollie.carp.vtt.core.service.model.Party;
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
public interface PartyPersistencePort {
	Party create(String name);

	void deleteById(UUID id);

	Optional<Party> findById(UUID id);

	List<Party> list();

	Party update(Party toSave);
}
