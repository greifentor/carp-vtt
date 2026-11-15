package de.ollie.carp.vtt.restclient.adapter;

import de.ollie.carp.vtt.core.service.model.Coordinates;
import de.ollie.carp.vtt.core.service.model.CoordinatesImpl;
import de.ollie.carp.vtt.core.service.model.TokenMapPartyScenario;
import de.ollie.carp.vtt.core.service.model.event.TokenPositionRemoveEvent;
import de.ollie.carp.vtt.core.service.model.event.TokenPositionUpdateEvent;
import de.ollie.carp.vtt.core.service.model.event.TokenUpdateEvent;
import de.ollie.carp.vtt.core.service.port.persistence.TokenUpdatePersistencePort;
import de.ollie.carp.vtt.core.service.port.web.TokenPositionWebPort;
import de.ollie.carp.vtt.restclient.TokenClient;
import de.ollie.carp.vtt.restclient.TokenPositionClient;
import de.ollie.carp.vtt.restclient.UnselectClient;
import jakarta.inject.Named;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;

@Named
@RequiredArgsConstructor
public class TokenPositionWebAdapter implements TokenPositionWebPort {

	private final TokenClient tokenClient;
	private final TokenPositionClient tokenPositionClient;
	private final TokenUpdatePersistencePort tokenUpdatePersistencePort;
	private final UnselectClient unselectClient;

	@Override
	public void pushTokenPositionRemove(TokenPositionRemoveEvent tokenPositionRemoveEvent) {
		tokenPositionClient.removeTokenPosition(tokenPositionRemoveEvent);
	}

	@Override
	public void pushTokenPositionUpdate(TokenPositionUpdateEvent tokenPositionUpdateEvent) {
		tokenPositionClient.updateTokenPosition(tokenPositionUpdateEvent);
	}

	@Override
	public void pushTokenUpdate(TokenUpdateEvent tokenUpdateEvent) {
		tokenClient.updateToken(tokenUpdateEvent);
	}

	@Override
	public void unselect(UUID battleMapId, UUID partyId, UUID scenarioId) {
		unselectClient.unselect(battleMapId, partyId, scenarioId);
	}

	@Override
	public void synchronizeTokenUpdates(SynchronizationObserver observer) {
		List<TokenMapPartyScenario> tokenPositions = tokenUpdatePersistencePort.findAll();
		for (int i = 0, leni = tokenPositions.size(); i < leni; i++) {
			TokenMapPartyScenario tokenPosition = tokenPositions.get(i);
			Coordinates coordinates = new CoordinatesImpl();
			coordinates.setFieldX(tokenPosition.getFieldX());
			coordinates.setFieldY(tokenPosition.getFieldY());
			pushTokenPositionUpdate(
				new TokenPositionUpdateEvent(
					tokenPosition.getId(),
					tokenPosition.getToken(),
					tokenPosition.getBattleMap(),
					tokenPosition.getCounter(),
					coordinates,
					tokenPosition.getParty(),
					tokenPosition.getScenario(),
					false
				)
			);
			if (observer != null) {
				observer.progress(i, leni - 1);
			}
		}
	}
}
