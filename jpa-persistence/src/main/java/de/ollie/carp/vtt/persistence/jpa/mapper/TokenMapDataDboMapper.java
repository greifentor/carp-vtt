package de.ollie.carp.vtt.persistence.jpa.mapper;

import de.ollie.carp.vtt.core.service.model.TokenMapData;
import de.ollie.carp.vtt.core.service.model.TokenMapDataImpl;
import de.ollie.carp.vtt.persistence.jpa.dbo.TokenMapDataDbo;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.ObjectFactory;

/**
 * GENERATED CODE - DO NOT TOUCH
 *
 * Remove this comment to suspend class from generation process.
 */
@Mapper(componentModel = "spring")
public interface TokenMapDataDboMapper {
	TokenMapData toModel(TokenMapDataDbo dbo);

	List<TokenMapData> toModels(List<TokenMapDataDbo> dbo);

	TokenMapDataDbo toDbo(TokenMapData model);

	List<TokenMapDataDbo> toDbos(List<TokenMapData> models);

	@ObjectFactory
	default TokenMapData createTokenMapData() {
		return new TokenMapDataImpl();
	}
}
