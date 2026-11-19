package de.ollie.carp.vtt.restserver.persistence.jpa.dbo;

import java.util.UUID;

public record MapPartyScenarioDbo(UUID battleMapId, UUID partyId, UUID scenarioId) {}
