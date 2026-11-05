package de.ollie.carp.vtt.restclient;

import de.ollie.carp.vtt.core.service.exception.UploadException;
import de.ollie.carp.vtt.core.service.model.Scenario;
import de.ollie.carp.vtt.restclient.api.ScenarioApi;
import de.ollie.carp.vtt.restclient.config.RestClientConfiguration;
import de.ollie.carp.vtt.restclient.mapper.ScenarioDtoMapper;
import de.ollie.carp.vtt.restclient.model.ScenarioDto;
import jakarta.inject.Named;
import java.util.UUID;
import lombok.RequiredArgsConstructor;

@Named
@RequiredArgsConstructor
public class ScenarioClient {

	private final BearerTokenGenerator bearerTokenGenerator;
	private final RestClientConfiguration restClientConfiguration;
	private final ScenarioDtoMapper scenarioDtoMapper;

	public void updateScenario(Scenario scenario) {
		ApiClient client = new ApiClient();
		client.setBasePath(restClientConfiguration.getBaseUrl());
		client.setBearerToken(bearerTokenGenerator.create());
		ScenarioApi api = new ScenarioApi(client);
		ScenarioDto dto = scenarioDtoMapper.map(scenario);
		try {
			ApiResponse<ScenarioDto> response = api.updateScenarioWithHttpInfo(dto);
			if (response.getStatusCode() / 100 != 2) {
				throw new UploadException("API access denied with code: " + response.getStatusCode());
			}
		} catch (ApiException ae) {
			throw new UploadException("token upload error: " + ae.getMessage());
		}
	}

	public void removeScenario(UUID idToDelete) {
		ApiClient client = new ApiClient();
		client.setBasePath(restClientConfiguration.getBaseUrl());
		client.setBearerToken(bearerTokenGenerator.create());
		ScenarioApi api = new ScenarioApi(client);
		try {
			api.removeScenario(idToDelete);
		} catch (ApiException ae) {
			throw new UploadException("token upload error: " + ae.getMessage());
		}
	}
}
