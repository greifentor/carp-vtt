package de.ollie.carp.vtt.restserver.core.service.port.persistence;

import de.ollie.carp.vtt.restserver.core.service.model.Party;
import de.ollie.carp.vtt.restserver.core.service.model.RuleSystem;
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
	Party create(String name, RuleSystem ruleSystem);

	void deleteById(UUID id);

	Optional<Party> findById(UUID id);

	List<Party> list();

	Party update(Party toSave);
}
