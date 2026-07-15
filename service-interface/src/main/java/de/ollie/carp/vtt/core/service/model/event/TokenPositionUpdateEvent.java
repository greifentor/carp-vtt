package de.ollie.carp.vtt.core.service.model.event;

import static de.ollie.baselib.util.Check.ensure;

import de.ollie.carp.vtt.core.service.model.BattleMap;
import de.ollie.carp.vtt.core.service.model.Coordinates;
import de.ollie.carp.vtt.core.service.model.Party;
import de.ollie.carp.vtt.core.service.model.Scenario;
import de.ollie.carp.vtt.core.service.model.Token;
import java.util.UUID;

public record TokenPositionUpdateEvent(
	UUID id,
	Token token,
	BattleMap battleMap,
	Coordinates coordinates,
	Party party,
	Scenario scenario
) {
	public TokenPositionUpdateEvent {
		ensure(coordinates != null, "coordinates cannot be null!");
		ensure(id != null, "id cannot be null!");
		ensure(battleMap != null, "map cannot be null!");
		ensure(party != null, "party cannot be null!");
		ensure(scenario != null, "scenario cannot be null!");
		ensure(token != null, "token cannot be null!");
	}

	public UUID getMapId() {
		return battleMap().getId();
	}

	public UUID getPartyId() {
		return party().getId();
	}

	public UUID getScenarioId() {
		return scenario().getId();
	}

	public UUID getTokenId() {
		return token().getId();
	}
}
