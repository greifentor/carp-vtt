package de.ollie.carp.vtt.restclient.mapper;

import de.ollie.carp.vtt.core.service.model.event.TokenUpdateEvent;
import de.ollie.carp.vtt.restclient.model.TokenDto;
import de.ollie.carp.vtt.restclient.model.TokenSizeDto;
import jakarta.inject.Named;

@Named
public class TokenDtoMapper {

	public TokenDto map(TokenUpdateEvent tokenUpdateEvent) {
		TokenDto dto = new TokenDto();
		dto.setId(tokenUpdateEvent.getId());
		dto.setImageContent(tokenUpdateEvent.getToken().getImage());
		dto.setName(tokenUpdateEvent.getToken().getName());
		dto.setTokenSize(TokenSizeDto.valueOf(tokenUpdateEvent.getToken().getTokenSize().name()));
		return dto;
	}
}
