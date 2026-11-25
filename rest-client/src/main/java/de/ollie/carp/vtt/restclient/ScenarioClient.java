package de.ollie.carp.vtt.restclient;

import de.ollie.carp.vtt.core.service.exception.UploadException;
import de.ollie.carp.vtt.core.service.model.Scenario;
import de.ollie.carp.vtt.restclient.api.ScenarioApi;
import de.ollie.carp.vtt.restclient.config.RestClientConfiguration;
import de.ollie.carp.vtt.restclient.mapper.ScenarioDtoMapper;
import de.ollie.carp.vtt.restclient.model.ScenarioDto;
import jakarta.inject.Named;
import java.util.UUID;
import lombok.Generated;
import lombok.RequiredArgsConstructor;

/**
 * GENERATED CODE - DO NOT TOUCH
 *
 * Remove this comment to suspend class from generation process.
 */
@Generated
@Named
@RequiredArgsConstructor
public class ScenarioClient {

	private final BearerTokenGenerator bearerTokenGenerator;
	private final RestClientConfiguration restClientConfiguration;
	private final ScenarioDtoMapper mapper;

	public void updateScenario(Scenario model) {
		ScenarioApi api = getApiClient();
		ScenarioDto dto = mapper.map(model);
		try {
			ApiResponse<ScenarioDto> response = api.updateScenarioWithHttpInfo(dto);
			if (response.getStatusCode() / 100 != 2) {
				throw new UploadException("API access denied with code: " + response.getStatusCode());
			}
		} catch (ApiException ae) {
			throw new UploadException("scenario upload error: " + ae.getMessage());
		}
	}

	private ScenarioApi getApiClient() {
		ApiClient client = new ApiClient();
		client.setBasePath(restClientConfiguration.getBaseUrl());
		client.setBearerToken(bearerTokenGenerator.create());
		return new ScenarioApi(client);
	}

	public void removeScenario(UUID idToDelete) {
		try {
			ScenarioApi api = getApiClient();
			api.removeScenario(idToDelete);
		} catch (ApiException ae) {
			throw new UploadException("scenario upload error: " + ae.getMessage());
		}
	}
}