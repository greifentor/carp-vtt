package de.ollie.carp.vtt.restclient.adapters;

import de.ollie.carp.vtt.core.service.model.event.TokenPositionUpdateEvent;
import de.ollie.carp.vtt.core.service.port.web.TokenWebPort;
import de.ollie.carp.vtt.restclient.TokenPositionClient;
import jakarta.inject.Named;
import lombok.RequiredArgsConstructor;

@Named
@RequiredArgsConstructor
public class TokenWebAdapter implements TokenWebPort {

	private final TokenPositionClient tokenPositionClient;

	@Override
	public void pushTokenPositionUpdate(TokenPositionUpdateEvent tokenPositionUpdateEvent) {
		tokenPositionClient.updateTokenPosition(tokenPositionUpdateEvent);
	}
}
