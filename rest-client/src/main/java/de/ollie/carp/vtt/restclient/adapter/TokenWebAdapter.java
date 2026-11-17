package de.ollie.carp.vtt.restclient.adapter;

import de.ollie.carp.vtt.core.service.model.Token;
import de.ollie.carp.vtt.core.service.model.event.TokenUpdateEvent;
import de.ollie.carp.vtt.core.service.port.web.TokenWebPort;
import de.ollie.carp.vtt.restclient.TokenClient;
import jakarta.inject.Named;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;

@Named
@RequiredArgsConstructor
public class TokenWebAdapter implements TokenWebPort {

	private final TokenClient tokenClient;

	@Override
	public void pushToken(Token toPush) {
		tokenClient.updateToken(new TokenUpdateEvent(toPush.getId(), toPush));
	}

	@Override
	public void deleteToken(UUID idToDelete) {
		// TODO Auto-generated method stub

	}

	@Override
	public void synchronizeTokens(SynchronizationObserver observer, List<Token> tokens) {
		for (int i = 0, leni = tokens.size(); i < leni; i++) {
			tokenClient.updateToken(new TokenUpdateEvent(UUID.randomUUID(), tokens.get(i)));
			if (observer != null) {
				observer.progress(i, leni - 1);
			}
		}
	}
}
