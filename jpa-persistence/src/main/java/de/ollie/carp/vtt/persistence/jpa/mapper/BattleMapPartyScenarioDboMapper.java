package de.ollie.carp.vtt.persistence.jpa.mapper;

import de.ollie.carp.vtt.core.service.model.BattleMapPartyScenario;
import de.ollie.carp.vtt.core.service.model.BattleMapPartyScenarioImpl;
import de.ollie.carp.vtt.persistence.jpa.dbo.BattleMapPartyScenarioDbo;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.ObjectFactory;

/**
 * GENERATED CODE - DO NOT TOUCH
 *
 * Remove this comment to suspend class from generation process.
 */
@Mapper(componentModel = "spring")
public interface BattleMapPartyScenarioDboMapper {
	BattleMapPartyScenario toModel(BattleMapPartyScenarioDbo dbo);

	List<BattleMapPartyScenario> toModels(List<BattleMapPartyScenarioDbo> dbo);

	BattleMapPartyScenarioDbo toDbo(BattleMapPartyScenario model);

	List<BattleMapPartyScenarioDbo> toDbos(List<BattleMapPartyScenario> models);

	@ObjectFactory
	default BattleMapPartyScenario createBattleMapPartyScenario() {
		return new BattleMapPartyScenarioImpl();
	}
}
