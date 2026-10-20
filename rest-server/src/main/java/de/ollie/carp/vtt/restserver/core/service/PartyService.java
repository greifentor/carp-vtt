package de.ollie.carp.vtt.restserver.core.service;

import de.ollie.carp.vtt.restserver.core.service.model.Party;
import de.ollie.carp.vtt.restserver.core.service.model.RuleSystem;
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
public interface PartyService {
	Party createParty(String name, RuleSystem ruleSystem);

	void deleteParty(UUID id);

	Optional<Party> findById(UUID id);

	List<Party> listPartys();

	Party updateParty(Party toSave);
}
