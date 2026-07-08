package de.ollie.carp.vtt.restserver.rest.mapper;

import de.ollie.carp.vtt.restserver.core.service.model.BattleMap;
import de.ollie.carp.vtt.restserver.rest.model.BattleMapDto;
import java.util.List;
import org.mapstruct.Mapper;

/**
 * GENERATED CODE - DO NOT TOUCH
 *
 * Remove this comment to suspend class from generation process.
 */
@Mapper(componentModel = "spring")
public interface BattleMapDtoMapper {
	BattleMap toModel(BattleMapDto dto);

	List<BattleMap> toModels(List<BattleMapDto> dto);

	BattleMapDto toDto(BattleMap model);

	List<BattleMapDto> toDtos(List<BattleMap> models);
}
