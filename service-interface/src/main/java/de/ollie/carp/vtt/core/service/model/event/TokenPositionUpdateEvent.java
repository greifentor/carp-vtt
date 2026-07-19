package de.ollie.carp.vtt.core.service.model.event;

import static de.ollie.baselib.util.Check.ensure;

import de.ollie.carp.vtt.core.service.model.BattleMap;
import de.ollie.carp.vtt.core.service.model.Coordinates;
import de.ollie.carp.vtt.core.service.model.Party;
import de.ollie.carp.vtt.core.service.model.Scenario;
import de.ollie.carp.vtt.core.service.model.Token;
import java.util.UUID;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@EqualsAndHashCode
@Getter
public class TokenPositionUpdateEvent {

	@Setter
	private UUID id;

	private Token token;
	private BattleMap battleMap;
	private Coordinates coordinates;
	private Party party;
	private Scenario scenario;

	public TokenPositionUpdateEvent(
		UUID id,
		Token token,
		BattleMap battleMap,
		Coordinates coordinates,
		Party party,
		Scenario scenario
	) {
		ensure(coordinates != null, "coordinates cannot be null!");
		ensure(battleMap != null, "map cannot be null!");
		ensure(id != null, "id cannot be null!");
		ensure(party != null, "party cannot be null!");
		ensure(scenario != null, "scenario cannot be null!");
		ensure(token != null, "token cannot be null!");
		this.coordinates = coordinates;
		this.battleMap = battleMap;
		this.id = id;
		this.party = party;
		this.scenario = scenario;
		this.token = token;
	}

	public UUID getBattleMapId() {
		return battleMap != null ? battleMap.getId() : null;
	}

	public UUID getPartyId() {
		return party != null ? party.getId() : null;
	}

	public UUID getScenarioId() {
		return scenario != null ? scenario.getId() : null;
	}

	public UUID getTokenId() {
		return token != null ? token.getId() : null;
	}
}
