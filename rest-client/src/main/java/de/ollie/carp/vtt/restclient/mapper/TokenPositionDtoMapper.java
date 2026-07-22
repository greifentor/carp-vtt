package de.ollie.carp.vtt.restclient.mapper;

import de.ollie.carp.vtt.core.service.model.event.TokenPositionUpdateEvent;
import de.ollie.carp.vtt.restclient.model.TokenPositionDto;
import jakarta.inject.Named;

@Named
public class TokenPositionDtoMapper {

	public TokenPositionDto map(TokenPositionUpdateEvent tokenPositionUpdateEvent) {
		TokenPositionDto dto = new TokenPositionDto();
		dto.setId(tokenPositionUpdateEvent.getId());
		dto.setBattleMapId(tokenPositionUpdateEvent.getBattleMapId());
		dto.setCoordinateX(tokenPositionUpdateEvent.getCoordinates().getFieldX().intValue());
		dto.setCoordinateY(tokenPositionUpdateEvent.getCoordinates().getFieldY().intValue());
		dto.setPartyId(tokenPositionUpdateEvent.getPartyId());
		dto.setScenarioId(tokenPositionUpdateEvent.getScenarioId());
		dto.setTokenId(tokenPositionUpdateEvent.getTokenId());
		return dto;
	}
}
