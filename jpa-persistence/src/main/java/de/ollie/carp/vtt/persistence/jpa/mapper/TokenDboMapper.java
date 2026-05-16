package de.ollie.carp.vtt.persistence.jpa.mapper;

import de.ollie.carp.vtt.core.service.model.Token;
import de.ollie.carp.vtt.persistence.jpa.dbo.TokenDbo;
import java.util.List;
import org.mapstruct.Mapper;

/**
 * GENERATED CODE - DO NOT TOUCH
 *
 * Remove this comment to suspend class from generation process.
 */
@Mapper(componentModel = "spring")
public interface TokenDboMapper {
	Token toModel(TokenDbo dbo);

	List<Token> toModels(List<TokenDbo> dbo);

	TokenDbo toDbo(Token model);

	List<TokenDbo> toDbos(List<Token> models);
}
