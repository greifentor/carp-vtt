package de.ollie.carp.vtt.restclient;

import de.ollie.carp.vtt.core.service.exception.UploadException;
import de.ollie.carp.vtt.restclient.api.UserRightsApi;
import de.ollie.carp.vtt.restclient.config.RestClientConfiguration;
import jakarta.inject.Named;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;

@Named
@RequiredArgsConstructor
public class UserRightsClient {

	private final BearerTokenGenerator bearerTokenGenerator;
	private final RestClientConfiguration restClientConfiguration;

	public List<String> getUserRights(UUID userId) {
		ApiClient client = new ApiClient();
		client.setBasePath(restClientConfiguration.getBaseUrl());
		client.setBearerToken(bearerTokenGenerator.create());
		UserRightsApi api = new UserRightsApi(client);
		try {
			return api.getUserRights(userId);
		} catch (ApiException ae) {
			throw new UploadException("unselect error: " + ae.getMessage());
		}
	}
}
