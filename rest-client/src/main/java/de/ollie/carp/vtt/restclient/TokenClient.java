package de.ollie.carp.vtt.restclient;

import de.ollie.carp.vtt.core.service.exception.UploadException;
import de.ollie.carp.vtt.core.service.model.event.TokenUpdateEvent;
import de.ollie.carp.vtt.restclient.api.TokenApi;
import de.ollie.carp.vtt.restclient.config.RestClientConfiguration;
import de.ollie.carp.vtt.restclient.mapper.TokenDtoMapper;
import de.ollie.carp.vtt.restclient.model.TokenDto;
import jakarta.inject.Named;
import lombok.RequiredArgsConstructor;

@Named
@RequiredArgsConstructor
public class TokenClient {

	private final BearerTokenGenerator bearerTokenGenerator;
	private final RestClientConfiguration restClientConfiguration;
	private final TokenDtoMapper tokenDtoMapper;

	public void updateToken(TokenUpdateEvent tokenUpdateEvent) {
		ApiClient client = new ApiClient();
		client.setBasePath(restClientConfiguration.getBaseUrl());
		client.setBearerToken(bearerTokenGenerator.create());
		TokenApi api = new TokenApi(client);
		TokenDto dto = tokenDtoMapper.map(tokenUpdateEvent);
		try {
			api.updateToken(dto);
		} catch (ApiException ae) {
			throw new UploadException("token upload error: " + ae.getMessage());
		}
	}
}
