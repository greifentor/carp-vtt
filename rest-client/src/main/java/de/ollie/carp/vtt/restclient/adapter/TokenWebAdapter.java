package de.ollie.carp.vtt.restclient.adapter;

import de.ollie.carp.vtt.core.service.TokenService;
import de.ollie.carp.vtt.core.service.model.Token;
import de.ollie.carp.vtt.core.service.model.event.TokenPositionUpdateEvent;
import de.ollie.carp.vtt.core.service.model.event.TokenUpdateEvent;
import de.ollie.carp.vtt.core.service.port.web.TokenWebPort;
import de.ollie.carp.vtt.restclient.TokenClient;
import de.ollie.carp.vtt.restclient.TokenPositionClient;
import jakarta.inject.Named;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;

@Named
@RequiredArgsConstructor
public class TokenWebAdapter implements TokenWebPort {

	private final TokenClient tokenClient;
	private final TokenPositionClient tokenPositionClient;
	private final TokenService tokenService;

	@Override
	public void pushTokenPositionUpdate(TokenPositionUpdateEvent tokenPositionUpdateEvent) {
		tokenPositionClient.updateTokenPosition(tokenPositionUpdateEvent);
	}

	@Override
	public void pushTokenUpdate(TokenUpdateEvent tokenUpdateEvent) {
		tokenClient.updateToken(tokenUpdateEvent);
	}

	@Override
	public void synchronize(SynchronizationObserver observer) {
		List<Token> tokens = tokenService.findAll();
		for (int i = 0, leni = tokens.size(); i < leni; i++) {
			pushTokenUpdate(new TokenUpdateEvent(UUID.randomUUID(), tokens.get(i)));
			if (observer != null) {
				observer.progress(i, leni - 1);
			}
		}
	}
}
