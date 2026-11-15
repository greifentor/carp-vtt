package de.ollie.carp.vtt.restclient.mapper;

import de.ollie.carp.vtt.core.service.model.Party;
import de.ollie.carp.vtt.restclient.model.PartyDto;
import jakarta.inject.Named;

@Named
public class PartyDtoMapper {

	public PartyDto map(Party model) {
		PartyDto dto = new PartyDto();
		dto.setId(model.getId());
		dto.setName(model.getName());
		dto.setOwner(model.getOwner());
		return dto;
	}
}
