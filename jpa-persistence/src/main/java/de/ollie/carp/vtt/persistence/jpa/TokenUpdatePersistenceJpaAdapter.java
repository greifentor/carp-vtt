package de.ollie.carp.vtt.persistence.jpa;

import de.ollie.carp.vtt.core.service.model.Coordinates;
import de.ollie.carp.vtt.core.service.port.persistence.TokenUpdatePersistencePort;
import jakarta.inject.Named;
import java.util.UUID;

@Named
public class TokenUpdatePersistenceJpaAdapter implements TokenUpdatePersistencePort {

	@Override
	public void updateTokenPosition(UUID tokenId, UUID mapId, UUID partyId, UUID scenarioId, Coordinates coordinates) {
		System.out.println("New token position may be stored somewhere in the future!");
	}
}
