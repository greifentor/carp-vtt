package de.ollie.carp.vtt.restserver.core.service.model;

import java.util.UUID;

public record MapPartyScenario(UUID battleMapId, UUID partyId, UUID scenarioId) {}
