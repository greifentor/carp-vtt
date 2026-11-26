package de.ollie.carp.vtt.restclient.mapper;

import de.ollie.carp.vtt.core.service.model.BattleMap;
import de.ollie.carp.vtt.restclient.model.BattleMapDto;
import jakarta.inject.Named;

@Named
public class BattleMapDtoMapper {

	public BattleMapDto map(BattleMap battleMap) {
		BattleMapDto dto = new BattleMapDto();
		dto.setId(battleMap.getId());
		dto.setImage(battleMap.getImageContent());
		dto.setName(battleMap.getName());
		return dto;
	}
}
