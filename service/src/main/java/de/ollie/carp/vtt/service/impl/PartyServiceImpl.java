package de.ollie.carp.vtt.service.impl;

import de.ollie.carp.vtt.core.service.PartyService;
import de.ollie.carp.vtt.core.service.model.Party;
import de.ollie.carp.vtt.core.service.port.persistence.PartyPersistencePort;
import jakarta.inject.Named;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;

@Named
@RequiredArgsConstructor
class PartyServiceImpl implements PartyService {

	private final PartyPersistencePort partyPersistencePort;

	@Override
	public Optional<Party> findById(UUID id) {
		return partyPersistencePort.findById(id);
	}
}
