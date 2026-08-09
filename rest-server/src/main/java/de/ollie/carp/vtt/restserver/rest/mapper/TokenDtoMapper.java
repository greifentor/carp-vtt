package de.ollie.carp.vtt.restserver.rest.mapper;

import de.ollie.carp.vtt.restserver.core.service.model.Token;
import de.ollie.carp.vtt.restserver.rest.model.TokenDto;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * GENERATED CODE - DO NOT TOUCH
 *
 * Remove this comment to suspend class from generation process.
 */
@Mapper(componentModel = "spring")
public interface TokenDtoMapper {
	@Mapping(source = "imageContent", target = "image")
	Token toModel(TokenDto dto);

	List<Token> toModels(List<TokenDto> dto);

	@Mapping(source = "image", target = "imageContent")
	TokenDto toDto(Token model);

	List<TokenDto> toDtos(List<Token> models);
}
