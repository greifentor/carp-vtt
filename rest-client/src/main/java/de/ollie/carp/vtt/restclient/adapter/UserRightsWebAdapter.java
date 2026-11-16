package de.ollie.carp.vtt.restclient.adapter;

import de.ollie.carp.vtt.core.service.port.web.UserRightsWebPort;
import de.ollie.carp.vtt.restclient.UserRightsClient;
import jakarta.inject.Named;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;

@Named
@RequiredArgsConstructor
class UserRightsWebAdapter implements UserRightsWebPort {

	private final UserRightsClient client;

	@Override
	public List<String> getUserRights(UUID userId) {
		return client.getUserRights(userId);
	}
}
