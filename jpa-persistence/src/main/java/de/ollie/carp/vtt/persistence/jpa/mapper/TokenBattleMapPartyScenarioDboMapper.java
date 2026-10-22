package de.ollie.carp.vtt.persistence.jpa.mapper;

import de.ollie.carp.vtt.core.service.model.TokenBattleMapPartyScenario;
import de.ollie.carp.vtt.core.service.model.TokenBattleMapPartyScenarioImpl;
import de.ollie.carp.vtt.persistence.jpa.dbo.TokenBattleMapPartyScenarioDbo;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.ObjectFactory;

/**
 * GENERATED CODE - DO NOT TOUCH
 *
 * Remove this comment to suspend class from generation process.
 */
@Mapper(componentModel = "spring")
public interface TokenBattleMapPartyScenarioDboMapper {
	TokenBattleMapPartyScenario toModel(TokenBattleMapPartyScenarioDbo dbo);

	List<TokenBattleMapPartyScenario> toModels(List<TokenBattleMapPartyScenarioDbo> dbo);

	TokenBattleMapPartyScenarioDbo toDbo(TokenBattleMapPartyScenario model);

	List<TokenBattleMapPartyScenarioDbo> toDbos(List<TokenBattleMapPartyScenario> models);

	@ObjectFactory
	default TokenBattleMapPartyScenario createTokenBattleMapPartyScenarioData() {
		return new TokenBattleMapPartyScenarioImpl();
	}
}
