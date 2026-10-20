package de.ollie.carp.vtt.persistence.jpa.mapper;

import de.ollie.carp.vtt.core.service.model.BattleMap;
import de.ollie.carp.vtt.core.service.model.BattleMapImpl;
import de.ollie.carp.vtt.core.service.model.Party;
import de.ollie.carp.vtt.core.service.model.PartyImpl;
import de.ollie.carp.vtt.core.service.model.Scenario;
import de.ollie.carp.vtt.core.service.model.ScenarioImpl;
import de.ollie.carp.vtt.core.service.model.Token;
import de.ollie.carp.vtt.core.service.model.TokenImpl;
import de.ollie.carp.vtt.core.service.model.TokenMapPartyScenario;
import de.ollie.carp.vtt.persistence.jpa.dbo.TokenMapPartyScenarioDbo;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.ObjectFactory;

@Mapper(componentModel = "spring")
public interface TokenMapPartyScenarioDboMapper {
	TokenMapPartyScenario toModel(TokenMapPartyScenarioDbo dbo);

	List<TokenMapPartyScenario> toModels(Iterable<TokenMapPartyScenarioDbo> dbo);

	TokenMapPartyScenarioDbo toDbo(TokenMapPartyScenario model);

	List<TokenMapPartyScenarioDbo> toDbos(List<TokenMapPartyScenario> models);

	@ObjectFactory
	default BattleMap createBattleMap() {
		return new BattleMapImpl();
	}

	@ObjectFactory
	default Party createParty() {
		return new PartyImpl();
	}

	@ObjectFactory
	default Scenario createScenario() {
		return new ScenarioImpl();
	}

	@ObjectFactory
	default Token createToken() {
		return new TokenImpl();
	}
}
