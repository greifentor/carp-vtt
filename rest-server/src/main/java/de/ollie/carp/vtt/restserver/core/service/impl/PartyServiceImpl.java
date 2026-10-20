package de.ollie.carp.vtt.restserver.core.service.impl;

import de.ollie.carp.vtt.restserver.core.service.PartyService;
import de.ollie.carp.vtt.restserver.core.service.model.Party;
import de.ollie.carp.vtt.restserver.core.service.model.RuleSystem;
import de.ollie.carp.vtt.restserver.core.service.port.persistence.PartyPersistencePort;
import jakarta.inject.Named;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import lombok.Generated;
import lombok.RequiredArgsConstructor;

/**
 * GENERATED CODE - DO NOT TOUCH
 *
 * Remove this comment to suspend class from generation process.
 */
@Generated
@Named
@RequiredArgsConstructor
class PartyServiceImpl implements PartyService {

	private final PartyPersistencePort partyPersistencePort;

	@Override
	public Party createParty(String name, RuleSystem ruleSystem) {
		return partyPersistencePort.create(name, ruleSystem);
	}

	@Override
	public void deleteParty(UUID id) {
		partyPersistencePort.deleteById(id);
	}

	@Override
	public Optional<Party> findById(UUID id) {
		return partyPersistencePort.findById(id);
	}

	@Override
	public List<Party> listPartys() {
		return partyPersistencePort.list();
	}

	@Override
	public Party updateParty(Party toSave) {
		return partyPersistencePort.update(toSave);
	}
}
