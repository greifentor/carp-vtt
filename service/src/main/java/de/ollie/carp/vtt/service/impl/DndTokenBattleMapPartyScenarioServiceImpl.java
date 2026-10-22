package de.ollie.carp.vtt.service.impl;

import de.ollie.carp.vtt.core.service.DndTokenBattleMapPartyScenarioService;
import de.ollie.carp.vtt.core.service.model.DndTokenBattleMapPartyScenarioData;
import de.ollie.carp.vtt.core.service.model.DndTokenBattleMapPartyScenarioDataImpl;
import de.ollie.carp.vtt.core.service.model.TokenBattleMapPartyScenario;
import de.ollie.carp.vtt.core.service.port.persistence.DndTokenBattleMapPartyScenarioDataPersistencePort;
import de.ollie.carp.vtt.core.service.port.persistence.TokenBattleMapPartyScenarioPersistencePort;
import jakarta.inject.Named;
import java.util.NoSuchElementException;
import java.util.UUID;
import lombok.RequiredArgsConstructor;

@Named
@RequiredArgsConstructor
public class DndTokenBattleMapPartyScenarioServiceImpl implements DndTokenBattleMapPartyScenarioService {

	private final DndTokenBattleMapPartyScenarioDataPersistencePort dndTokenBattleMapPartyScenarioDataPersistencePort;
	private final TokenBattleMapPartyScenarioPersistencePort tokenBattleMapPartyScenarioDataPersistencePort;

	@Override
	public DndTokenBattleMapPartyScenarioData getByBattleMapPartyScenarioToken(
		UUID battleMapId,
		UUID partyId,
		UUID scenarioId,
		UUID tokenId
	) {
		TokenBattleMapPartyScenario tbmpsd = tokenBattleMapPartyScenarioDataPersistencePort
			.findByBattleMapPartyScenarioToken(battleMapId, partyId, scenarioId, tokenId)
			.orElseThrow(() ->
				new NoSuchElementException(
					"No TokenBattleMapPartyScenarioData found with battleMapId, partyId, scenarioId, tokenId: " +
					battleMapId +
					", " +
					partyId +
					", " +
					scenarioId +
					", " +
					tokenId
				)
			);
		return dndTokenBattleMapPartyScenarioDataPersistencePort
			.findById(tbmpsd.getId())
			.orElseGet(() -> new DndTokenBattleMapPartyScenarioDataImpl().setId(tbmpsd.getId()));
	}

	@Override
	public DndTokenBattleMapPartyScenarioData update(DndTokenBattleMapPartyScenarioData toSave) {
		return dndTokenBattleMapPartyScenarioDataPersistencePort.update(toSave);
	}
}
