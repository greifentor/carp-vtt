package de.ollie.carp.vtt.restclient;

import de.ollie.carp.vtt.core.service.exception.UploadException;
import de.ollie.carp.vtt.core.service.model.BattleMap;
import de.ollie.carp.vtt.restclient.api.BattleMapApi;
import de.ollie.carp.vtt.restclient.config.RestClientConfiguration;
import de.ollie.carp.vtt.restclient.mapper.BattleMapDtoMapper;
import de.ollie.carp.vtt.restclient.model.BattleMapDto;
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
public class BattleMapClient {

	private final BearerTokenGenerator bearerTokenGenerator;
	private final RestClientConfiguration restClientConfiguration;
	private final BattleMapDtoMapper mapper;

	public void updateBattleMap(BattleMap model) {
		BattleMapApi api = getApiClient();
		BattleMapDto dto = mapper.map(model);
		try {
			ApiResponse<BattleMapDto> response = api.updateBattleMapWithHttpInfo(dto);
			if (response.getStatusCode() / 100 != 2) {
				throw new UploadException("API access denied with code: " + response.getStatusCode());
			}
		} catch (ApiException ae) {
			throw new UploadException("battleMap upload error: " + ae.getMessage());
		}
	}

	private BattleMapApi getApiClient() {
		ApiClient client = new ApiClient();
		client.setBasePath(restClientConfiguration.getBaseUrl());
		client.setBearerToken(bearerTokenGenerator.create());
		return new BattleMapApi(client);
	}

	public void removeBattleMap(UUID idToDelete) {
		// try {
		// BattleMapApi api = getApiClient();
		// api.removeBattleMap(idToDelete);
		// } catch (ApiException ae) {
		// throw new UploadException("battleMap upload error: " + ae.getMessage());
		// }
	}
}
