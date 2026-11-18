package de.ollie.carp.vtt.persistence.jpa.dbo;

import java.util.UUID;

public record BattleMapPartyScenarioResult(UUID battleMapId, UUID partyId, UUID scenarioId) {}
