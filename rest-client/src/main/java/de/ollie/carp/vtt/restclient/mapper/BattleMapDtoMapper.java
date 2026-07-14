package de.ollie.carp.vtt.restclient.mapper;

import de.ollie.carp.vtt.core.service.model.BattleMap;
import de.ollie.carp.vtt.restclient.model.BattleMapDto;
import java.util.List;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BattleMapDtoMapper {
	BattleMap toModel(BattleMapDto dto);

	List<BattleMap> toModels(List<BattleMapDto> dbo);

	BattleMapDto toDto(BattleMap model);

	List<BattleMapDto> toDtos(List<BattleMap> models);
}
