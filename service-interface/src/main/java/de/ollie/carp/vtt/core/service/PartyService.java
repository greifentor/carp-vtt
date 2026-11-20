package de.ollie.carp.vtt.core.service;

import de.ollie.carp.vtt.core.service.model.Party;
import de.ollie.carp.vtt.core.service.model.RuleSystem;
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
	Party createParty(String name, UUID owner, RuleSystem ruleSystem);

	void deleteParty(UUID id);

	Optional<Party> findById(UUID id);

	List<Party> listPartys();

	Party updateParty(Party toSave);

	void updateAndPushParty(Party toSave);

	void pushParty(Party toPush);

	void pushDeleteParty(UUID idToDelete);

	void deleteAndPushDeleteParty(UUID idToDelete);
}
