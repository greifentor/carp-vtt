package de.ollie.carp.vtt.core.service;

import de.ollie.carp.vtt.core.service.model.DndTokenBattleMapPartyScenarioData;
import java.util.UUID;

public interface DndTokenBattleMapPartyScenarioService {
	DndTokenBattleMapPartyScenarioData getByBattleMapPartyScenarioToken(
		UUID battleMapId,
		UUID partyId,
		UUID scenarioId,
		UUID tokenId
	);

	DndTokenBattleMapPartyScenarioData update(DndTokenBattleMapPartyScenarioData toSave);
}
