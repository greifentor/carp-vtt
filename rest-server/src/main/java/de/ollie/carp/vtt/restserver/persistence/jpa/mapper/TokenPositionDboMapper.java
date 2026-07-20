package de.ollie.carp.vtt.restserver.persistence.jpa.mapper;

import de.ollie.carp.vtt.restserver.core.service.model.TokenPosition;
import de.ollie.carp.vtt.restserver.persistence.jpa.dbo.TokenPositionDbo;
import java.util.List;
import org.mapstruct.Mapper;

/**
 * GENERATED CODE - DO NOT TOUCH
 *
 * Remove this comment to suspend class from generation process.
 */
@Mapper(componentModel = "spring")
public interface TokenPositionDboMapper {
	TokenPosition toModel(TokenPositionDbo dbo);

	List<TokenPosition> toModels(List<TokenPositionDbo> dbo);

	TokenPositionDbo toDbo(TokenPosition model);

	List<TokenPositionDbo> toDbos(List<TokenPosition> models);
}
