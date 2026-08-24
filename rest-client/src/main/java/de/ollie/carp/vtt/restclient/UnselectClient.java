package de.ollie.carp.vtt.restclient;

import de.ollie.carp.vtt.core.service.exception.UploadException;
import de.ollie.carp.vtt.restclient.api.UnselectApi;
import de.ollie.carp.vtt.restclient.config.RestClientConfiguration;
import jakarta.inject.Named;
import java.util.UUID;
import lombok.RequiredArgsConstructor;

@Named
@RequiredArgsConstructor
public class UnselectClient {

	private final BearerTokenGenerator bearerTokenGenerator;
	private final RestClientConfiguration restClientConfiguration;

	public void unselect(UUID battleMapId, UUID partyId, UUID scenarioId) {
		ApiClient client = new ApiClient();
		client.setBasePath(restClientConfiguration.getBaseUrl());
		client.setBearerToken(bearerTokenGenerator.create());
		UnselectApi api = new UnselectApi(client);
		try {
			api.unselect(battleMapId, partyId, scenarioId);
		} catch (ApiException ae) {
			throw new UploadException("unselect error: " + ae.getMessage());
		}
	}
}
