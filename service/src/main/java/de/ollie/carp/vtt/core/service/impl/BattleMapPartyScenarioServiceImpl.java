package de.ollie.carp.vtt.core.service.impl;

import de.ollie.carp.vtt.core.service.BattleMapPartyScenarioService;
import de.ollie.carp.vtt.core.service.model.BattleMap;
import de.ollie.carp.vtt.core.service.model.BattleMapPartyScenario;
import de.ollie.carp.vtt.core.service.model.Party;
import de.ollie.carp.vtt.core.service.model.Scenario;
import de.ollie.carp.vtt.core.service.port.persistence.BattleMapPartyScenarioPersistencePort;
import jakarta.inject.Named;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import lombok.Generated;
import lombok.RequiredArgsConstructor;

/**
 * GENERATED CODE - DO NOT TOUCH
 *
 * Remove this comment to suspend class from generation process.
 */
@Generated
@Named
@RequiredArgsConstructor
class BattleMapPartyScenarioServiceImpl implements BattleMapPartyScenarioService {

	private final BattleMapPartyScenarioPersistencePort battleMapPartyScenarioPersistencePort;

	@Override
	public BattleMapPartyScenario createBattleMapPartyScenario(BattleMap battleMap, Party party, Scenario scenario) {
		return battleMapPartyScenarioPersistencePort.create(battleMap, party, scenario);
	}

	@Override
	public void deleteBattleMapPartyScenario(UUID id) {
		battleMapPartyScenarioPersistencePort.deleteById(id);
	}

	@Override
	public Optional<BattleMapPartyScenario> findById(UUID id) {
		return battleMapPartyScenarioPersistencePort.findById(id);
	}

	@Override
	public List<BattleMapPartyScenario> listBattleMapPartyScenarios() {
		return battleMapPartyScenarioPersistencePort.list();
	}

	@Override
	public BattleMapPartyScenario updateBattleMapPartyScenario(BattleMapPartyScenario toSave) {
		return battleMapPartyScenarioPersistencePort.update(toSave);
	}
}
