package de.ollie.vtt.persistence.jpa.mapper;

import de.ollie.vtt.core.service.model.Token;
import de.ollie.vtt.persistence.jpa.dbo.TokenDbo;
import org.mapstruct.Mapper;

/**
 * GENERATED CODE - DO NOT TOUCH
 *
 * Remove this comment to suspend class from generation process.
 */
@Mapper(componentModel = "spring")
public interface TokenDboMapper {
	Token toModel(TokenDbo dbo);
}
