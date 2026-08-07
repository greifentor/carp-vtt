package de.ollie.carp.vtt.restclient;

import de.ollie.carp.vtt.core.service.exception.UploadException;
import de.ollie.carp.vtt.core.service.model.event.BattleMapUpdateEvent;
import de.ollie.carp.vtt.restclient.api.BattleMapApi;
import de.ollie.carp.vtt.restclient.config.RestClientConfiguration;
import de.ollie.carp.vtt.restclient.mapper.BattleMapDtoMapper;
import de.ollie.carp.vtt.restclient.model.BattleMapDto;
import jakarta.inject.Named;
import lombok.RequiredArgsConstructor;

@Named
@RequiredArgsConstructor
public class BattleMapClient {

	private final BearerTokenGenerator bearerTokenGenerator;
	private final RestClientConfiguration restClientConfiguration;
	private final BattleMapDtoMapper battleMapDtoMapper;

	public void updateBattleMap(BattleMapUpdateEvent battleMapUpdateEvent) {
		ApiClient client = new ApiClient();
		client.setBasePath(restClientConfiguration.getBaseUrl());
		client.setBearerToken(bearerTokenGenerator.create());
		BattleMapApi api = new BattleMapApi(client);
		BattleMapDto dto = battleMapDtoMapper.map(battleMapUpdateEvent);
		try {
			api.updateBattleMap(dto);
		} catch (ApiException ae) {
			throw new UploadException("battle map upload error: " + ae.getMessage());
		}
	}
}
