package de.ollie.carp.vtt.core.service;

import de.ollie.carp.vtt.core.service.model.Party;
import java.util.Optional;
import java.util.UUID;

public interface PartyService {
	Optional<Party> findById(UUID id);
}
