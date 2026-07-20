package de.ollie.carp.vtt.restclient;

import de.ollie.carp.vtt.core.service.exception.UploadException;
import de.ollie.carp.vtt.core.service.model.event.TokenPositionUpdateEvent;
import de.ollie.carp.vtt.restclient.api.TokenPositionApi;
import de.ollie.carp.vtt.restclient.config.RestClientConfiguration;
import de.ollie.carp.vtt.restclient.mapper.TokenPositionDtoMapper;
import de.ollie.carp.vtt.restclient.model.TokenPositionDto;
import jakarta.inject.Named;
import lombok.RequiredArgsConstructor;

@Named
@RequiredArgsConstructor
public class TokenPositionClient {

	private final RestClientConfiguration restClientConfiguration;
	private final TokenPositionDtoMapper tokenPositionDtoMapper;

	public void updateTokenPosition(TokenPositionUpdateEvent tokenPositionUpdateEvent) {
		ApiClient client = new ApiClient();
		client.setBasePath(restClientConfiguration.getBaseUrl());
		TokenPositionApi api = new TokenPositionApi(client);
		TokenPositionDto dto = tokenPositionDtoMapper.map(tokenPositionUpdateEvent);
		try {
			api.updateTokenPosition(dto);
			System.out.println(dto.getId() + " - " + dto.getCoordinateX() + "," + dto.getCoordinateY() + " - updated");
		} catch (ApiException ae) {
			throw new UploadException("token position upload error: " + ae.getMessage());
		}
	}
}
