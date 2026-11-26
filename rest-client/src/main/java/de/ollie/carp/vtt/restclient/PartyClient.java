package de.ollie.carp.vtt.restclient;

import de.ollie.carp.vtt.core.service.exception.UploadException;
import de.ollie.carp.vtt.core.service.model.Party;
import de.ollie.carp.vtt.restclient.api.PartyApi;
import de.ollie.carp.vtt.restclient.config.RestClientConfiguration;
import de.ollie.carp.vtt.restclient.mapper.PartyDtoMapper;
import de.ollie.carp.vtt.restclient.model.PartyDto;
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
public class PartyClient {

	private final BearerTokenGenerator bearerTokenGenerator;
	private final RestClientConfiguration restClientConfiguration;
	private final PartyDtoMapper mapper;

	public void updateParty(Party model) {
		PartyApi api = getApiClient();
		PartyDto dto = mapper.map(model);
		try {
			ApiResponse<PartyDto> response = api.updatePartyWithHttpInfo(dto);
			if (response.getStatusCode() / 100 != 2) {
				throw new UploadException("API access denied with code: " + response.getStatusCode());
			}
		} catch (ApiException ae) {
			throw new UploadException("party upload error: " + ae.getMessage());
		}
	}

	private PartyApi getApiClient() {
		ApiClient client = new ApiClient();
		client.setBasePath(restClientConfiguration.getBaseUrl());
		client.setBearerToken(bearerTokenGenerator.create());
		return new PartyApi(client);
	}

	public void removeParty(UUID idToDelete) {
		try {
			PartyApi api = getApiClient();
			api.removeParty(idToDelete);
		} catch (ApiException ae) {
			throw new UploadException("party upload error: " + ae.getMessage());
		}
	}
}
