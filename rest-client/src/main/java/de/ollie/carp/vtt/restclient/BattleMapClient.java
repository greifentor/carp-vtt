package de.ollie.carp.vtt.restclient;

import de.ollie.carp.vtt.core.service.exception.UploadException;
import de.ollie.carp.vtt.core.service.model.BattleMap;
import de.ollie.carp.vtt.restclient.api.BattleMapApi;
import de.ollie.carp.vtt.restclient.config.RestClientConfiguration;
import de.ollie.carp.vtt.restclient.model.BattleMapDto;
import jakarta.inject.Named;
import lombok.RequiredArgsConstructor;

@Named
@RequiredArgsConstructor
public class BattleMapClient {

	private final RestClientConfiguration restClientConfiguration;

	public void uploadBattleMap(BattleMap battleMap) {
		ApiClient client = new ApiClient();
		client.setBasePath(restClientConfiguration.getBaseUrl());
		BattleMapApi api = new BattleMapApi(client);
		BattleMapDto dto = null;
		try {
			api.updateBattleMap(dto);
		} catch (ApiException ae) {
			throw new UploadException("battle map upload error: " + ae.getMessage());
		}
	}
}
