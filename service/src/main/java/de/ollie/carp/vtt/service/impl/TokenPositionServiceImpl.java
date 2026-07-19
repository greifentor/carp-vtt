package de.ollie.carp.vtt.service.impl;

import static de.ollie.baselib.util.Check.ensure;

import de.ollie.carp.vtt.core.service.TokenPositionService;
import de.ollie.carp.vtt.core.service.model.BattleMap;
import de.ollie.carp.vtt.core.service.model.Party;
import de.ollie.carp.vtt.core.service.model.Scenario;
import de.ollie.carp.vtt.core.service.model.TokenData;
import de.ollie.carp.vtt.core.service.model.event.TokenPositionUpdateEvent;
import de.ollie.carp.vtt.core.service.port.persistence.TokenUpdatePersistencePort;
import jakarta.inject.Named;
import java.util.List;
import lombok.RequiredArgsConstructor;

@Named
@RequiredArgsConstructor
class TokenPositionServiceImpl implements TokenPositionService {

	private final TokenUpdatePersistencePort tokenUpdatePort;

	@Override
	public List<TokenData> findAllBy(BattleMap battleMap, Party party, Scenario scenario) {
		ensure(battleMap != null, "battle map cannot be null!");
		ensure(party != null, "party cannot be null!");
		ensure(scenario != null, "scenario cannot be null!");
		return tokenUpdatePort.findAllByMapPartyScenario(battleMap.getId(), party.getId(), scenario.getId());
	}

	@Override
	public void updateTokenPosition(TokenPositionUpdateEvent tokenPositionUpdateEvent) {
		ensure(tokenPositionUpdateEvent != null, "token position update event cannot be null!");
		tokenPositionUpdateEvent.setId(
			tokenUpdatePort.updateTokenPosition(
				tokenPositionUpdateEvent.getId(),
				tokenPositionUpdateEvent.getTokenId(),
				tokenPositionUpdateEvent.getBattleMapId(),
				tokenPositionUpdateEvent.getPartyId(),
				tokenPositionUpdateEvent.getScenarioId(),
				tokenPositionUpdateEvent.getCoordinates()
			)
		);
	}
}
