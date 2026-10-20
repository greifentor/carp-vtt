package de.ollie.carp.vtt.restserver.rest.mapper;

import de.ollie.carp.vtt.restserver.core.service.model.Party;
import de.ollie.carp.vtt.restserver.rest.model.PartyDto;
import java.util.List;
import org.mapstruct.Mapper;

/**
 * GENERATED CODE - DO NOT TOUCH
 *
 * Remove this comment to suspend class from generation process.
 */
@Mapper(componentModel = "spring")
public interface PartyDtoMapper {
	Party toModel(PartyDto dto);

	List<Party> toModels(List<PartyDto> dto);

	PartyDto toDto(Party model);

	List<PartyDto> toDtos(List<Party> models);
}
