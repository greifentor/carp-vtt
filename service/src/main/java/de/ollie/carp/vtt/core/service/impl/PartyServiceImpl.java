package de.ollie.carp.vtt.core.service.impl;

import de.ollie.carp.vtt.core.service.PartyService;
import de.ollie.carp.vtt.core.service.model.Party;
import de.ollie.carp.vtt.core.service.model.RuleSystem;
import de.ollie.carp.vtt.core.service.port.persistence.PartyPersistencePort;
import de.ollie.carp.vtt.core.service.port.web.PartyWebPort;
import jakarta.inject.Named;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import lombok.Generated;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

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
	private final PartyWebPort partyWebPort;

	@Override
	public Party createParty(String name, UUID owner, RuleSystem ruleSystem) {
		return partyPersistencePort.create(name, owner, ruleSystem);
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

	@Override
	public void pushParty(Party toPush) {
		partyWebPort.pushParty(toPush);
	}

	@Override
	public void pushDeleteParty(UUID idToDelete) {
		partyWebPort.deleteParty(idToDelete);
	}

	@Override
	@Transactional
	public void updateAndPushParty(Party toSave) {
		partyWebPort.pushParty(toSave);
		partyPersistencePort.update(toSave);
	}

	@Override
	@Transactional
	public void deleteAndPushDeleteParty(UUID idToDelete) {
		partyWebPort.deleteParty(idToDelete);
		partyPersistencePort.deleteById(idToDelete);
	}
}
