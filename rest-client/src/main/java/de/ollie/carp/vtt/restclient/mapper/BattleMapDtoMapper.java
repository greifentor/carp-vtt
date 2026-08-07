package de.ollie.carp.vtt.restclient.mapper;

import de.ollie.carp.vtt.core.service.model.event.BattleMapUpdateEvent;
import de.ollie.carp.vtt.restclient.model.BattleMapDto;
import jakarta.inject.Named;

@Named
public class BattleMapDtoMapper {

	public BattleMapDto map(BattleMapUpdateEvent battleMapUpdateEvent) {
		BattleMapDto dto = new BattleMapDto();
		dto.setId(battleMapUpdateEvent.getId());
		dto.setImage(battleMapUpdateEvent.getBattleMap().getImageContent());
		dto.setName(battleMapUpdateEvent.getBattleMap().getName());
		return dto;
	}
}
