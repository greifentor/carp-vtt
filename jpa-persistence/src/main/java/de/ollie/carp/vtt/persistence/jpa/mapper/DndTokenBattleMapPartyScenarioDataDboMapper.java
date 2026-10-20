package de.ollie.carp.vtt.persistence.jpa.mapper;

import de.ollie.carp.vtt.core.service.model.DndTokenBattleMapPartyScenarioData;
import de.ollie.carp.vtt.core.service.model.DndTokenBattleMapPartyScenarioDataImpl;
import de.ollie.carp.vtt.persistence.jpa.dbo.DndTokenBattleMapPartyScenarioDataDbo;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.ObjectFactory;

/**
 * GENERATED CODE - DO NOT TOUCH
 *
 * Remove this comment to suspend class from generation process.
 */
@Mapper(componentModel = "spring")
public interface DndTokenBattleMapPartyScenarioDataDboMapper {
	DndTokenBattleMapPartyScenarioData toModel(DndTokenBattleMapPartyScenarioDataDbo dbo);

	List<DndTokenBattleMapPartyScenarioData> toModels(List<DndTokenBattleMapPartyScenarioDataDbo> dbo);

	DndTokenBattleMapPartyScenarioDataDbo toDbo(DndTokenBattleMapPartyScenarioData model);

	List<DndTokenBattleMapPartyScenarioDataDbo> toDbos(List<DndTokenBattleMapPartyScenarioData> models);

	@ObjectFactory
	default DndTokenBattleMapPartyScenarioData createDndTokenBattleMapPartyScenarioData() {
		return new DndTokenBattleMapPartyScenarioDataImpl();
	}
}
