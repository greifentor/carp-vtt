package de.ollie.carp.vtt.restserver.rest.mapper;

import de.ollie.carp.vtt.restserver.core.service.model.DndData;
import de.ollie.carp.vtt.restserver.rest.model.DndDataDto;
import java.util.List;
import org.mapstruct.Mapper;

/**
 * GENERATED CODE - DO NOT TOUCH
 *
 * Remove this comment to suspend class from generation process.
 */
@Mapper(componentModel = "spring")
public interface DndDataDtoMapper {
	DndData toModel(DndDataDto dto);

	List<DndData> toModels(List<DndDataDto> dto);

	DndDataDto toDto(DndData model);

	List<DndDataDto> toDtos(List<DndData> models);
}
