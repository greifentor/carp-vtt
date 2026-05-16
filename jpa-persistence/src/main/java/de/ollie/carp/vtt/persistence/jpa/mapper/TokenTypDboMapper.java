package de.ollie.carp.vtt.persistence.jpa.mapper;

import de.ollie.carp.vtt.core.service.model.TokenTyp;
import de.ollie.carp.vtt.persistence.jpa.dbo.TokenTypDbo;
import java.util.List;
import org.mapstruct.Mapper;

/**
 * GENERATED CODE - DO NOT TOUCH
 *
 * Remove this comment to suspend class from generation process.
 */
@Mapper(componentModel = "spring")
public interface TokenTypDboMapper {
	TokenTyp toModel(TokenTypDbo dbo);

	List<TokenTyp> toModels(List<TokenTypDbo> dbo);

	TokenTypDbo toDbo(TokenTyp model);

	List<TokenTypDbo> toDbos(List<TokenTyp> models);
}
