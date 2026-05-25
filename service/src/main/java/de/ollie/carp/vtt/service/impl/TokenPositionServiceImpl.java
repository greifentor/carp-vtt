package de.ollie.carp.vtt.service.impl;

import static de.ollie.baselib.util.Check.ensure;

import de.ollie.carp.vtt.core.service.TokenPositionService;
import de.ollie.carp.vtt.core.service.model.Map;
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
	public List<TokenData> findAllBy(Map map, Party party, Scenario scenario) {
		ensure(map != null, "map cannot be null!");
		ensure(party != null, "party cannot be null!");
		ensure(scenario != null, "scenario cannot be null!");
		return tokenUpdatePort.findAllByMapPartyScenario(map.getId(), party.getId(), scenario.getId());
	}

	@Override
	public void updateTokenPosition(TokenPositionUpdateEvent tokenPositionUpdateEvent) {
		ensure(tokenPositionUpdateEvent != null, "token position update event cannot be null!");
		tokenUpdatePort.updateTokenPosition(
			tokenPositionUpdateEvent.id(),
			tokenPositionUpdateEvent.getTokenId(),
			tokenPositionUpdateEvent.getMapId(),
			tokenPositionUpdateEvent.getPartyId(),
			tokenPositionUpdateEvent.getScenarioId(),
			tokenPositionUpdateEvent.coordinates()
		);
	}
}
