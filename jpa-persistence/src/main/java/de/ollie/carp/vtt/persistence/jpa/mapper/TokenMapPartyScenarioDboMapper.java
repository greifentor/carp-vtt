package de.ollie.carp.vtt.persistence.jpa.mapper;

import de.ollie.carp.vtt.core.service.model.TokenMapPartyScenario;
import de.ollie.carp.vtt.persistence.jpa.dbo.TokenMapPartyScenarioDbo;
import java.util.List;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TokenMapPartyScenarioDboMapper {
	TokenMapPartyScenario toModel(TokenMapPartyScenarioDbo dbo);

	List<TokenMapPartyScenario> toModels(Iterable<TokenMapPartyScenarioDbo> dbo);

	TokenMapPartyScenarioDbo toDbo(TokenMapPartyScenario model);

	List<TokenMapPartyScenarioDbo> toDbos(List<TokenMapPartyScenario> models);
}
