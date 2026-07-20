package de.ollie.carp.vtt.restserver.rest.mapper;

import de.ollie.carp.vtt.restserver.core.service.model.TokenPosition;
import de.ollie.carp.vtt.restserver.rest.model.TokenPositionDto;
import java.util.List;
import org.mapstruct.Mapper;

/**
 * GENERATED CODE - DO NOT TOUCH
 *
 * Remove this comment to suspend class from generation process.
 */
@Mapper(componentModel = "spring")
public interface TokenPositionDtoMapper {
	TokenPosition toModel(TokenPositionDto dto);

	List<TokenPosition> toModels(List<TokenPositionDto> dto);

	TokenPositionDto toDto(TokenPosition model);

	List<TokenPositionDto> toDtos(List<TokenPosition> models);
}
