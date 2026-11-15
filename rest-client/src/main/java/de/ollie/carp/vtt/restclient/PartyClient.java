package de.ollie.carp.vtt.restclient;

import de.ollie.carp.vtt.core.service.exception.UploadException;
import de.ollie.carp.vtt.core.service.model.Party;
import de.ollie.carp.vtt.restclient.api.PartyApi;
import de.ollie.carp.vtt.restclient.config.RestClientConfiguration;
import de.ollie.carp.vtt.restclient.mapper.PartyDtoMapper;
import de.ollie.carp.vtt.restclient.model.PartyDto;
import jakarta.inject.Named;
import java.util.UUID;
import lombok.RequiredArgsConstructor;

@Named
@RequiredArgsConstructor
public class PartyClient {

	private final BearerTokenGenerator bearerTokenGenerator;
	private final RestClientConfiguration restClientConfiguration;
	private final PartyDtoMapper mapper;

	public void updateParty(Party Party) {
		ApiClient client = new ApiClient();
		client.setBasePath(restClientConfiguration.getBaseUrl());
		client.setBearerToken(bearerTokenGenerator.create());
		PartyApi api = new PartyApi(client);
		PartyDto dto = mapper.map(Party);
		try {
			ApiResponse<PartyDto> response = api.updatePartyWithHttpInfo(dto);
			if (response.getStatusCode() / 100 != 2) {
				throw new UploadException("API access denied with code: " + response.getStatusCode());
			}
		} catch (ApiException ae) {
			throw new UploadException("party upload error: " + ae.getMessage());
		}
	}

	public void removeParty(UUID idToDelete) {
		ApiClient client = new ApiClient();
		client.setBasePath(restClientConfiguration.getBaseUrl());
		client.setBearerToken(bearerTokenGenerator.create());
		PartyApi api = new PartyApi(client);
		try {
			api.removeParty(idToDelete);
		} catch (ApiException ae) {
			throw new UploadException("party upload error: " + ae.getMessage());
		}
	}
}
