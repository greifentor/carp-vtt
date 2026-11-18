package de.ollie.carp.vtt.persistence.jpa;

import de.ollie.carp.vtt.core.service.model.BattleMap;
import de.ollie.carp.vtt.core.service.model.BattleMapPartyScenario;
import de.ollie.carp.vtt.core.service.model.Party;
import de.ollie.carp.vtt.core.service.model.Scenario;
import de.ollie.carp.vtt.core.service.port.persistence.BattleMapPartyScenarioPersistencePort;
import de.ollie.carp.vtt.core.service.port.persistence.BattleMapPersistencePort;
import de.ollie.carp.vtt.core.service.port.persistence.PartyPersistencePort;
import de.ollie.carp.vtt.core.service.port.persistence.ScenarioPersistencePort;
import de.ollie.carp.vtt.persistence.jpa.dbo.BattleMapPartyScenarioResult;
import de.ollie.carp.vtt.persistence.jpa.repository.BattleMapPartyScenarioRepository;
import jakarta.inject.Named;
import java.util.List;
import java.util.NoSuchElementException;
import lombok.RequiredArgsConstructor;

@Named
@RequiredArgsConstructor
class BattleMapPartyScenarioPersistenceJpaAdapter implements BattleMapPartyScenarioPersistencePort {

	private final BattleMapPartyScenarioRepository repository;
	private final BattleMapPersistencePort battleMapPersistencePort;
	private final PartyPersistencePort partyPersistencePort;
	private final ScenarioPersistencePort scenarioPersistencePort;

	@Override
	public List<BattleMapPartyScenario> findAll() {
		return repository.findAllBattleMapPartyScenarios().stream().map(this::createBattleMapPartyScenario).toList();
	}

	private BattleMapPartyScenario createBattleMapPartyScenario(BattleMapPartyScenarioResult bmpsr) {
		BattleMap battleMap = battleMapPersistencePort
			.findById(bmpsr.battleMapId())
			.orElseThrow(() -> new NoSuchElementException("No battleMap found with id: " + bmpsr.battleMapId()));
		Party party = partyPersistencePort
			.findById(bmpsr.partyId())
			.orElseThrow(() -> new NoSuchElementException("No party found with id: " + bmpsr.partyId()));
		Scenario scenario = scenarioPersistencePort
			.findById(bmpsr.scenarioId())
			.orElseThrow(() -> new NoSuchElementException("No scenario found with id: " + bmpsr.scenarioId()));
		return new BattleMapPartyScenario(battleMap, party, scenario);
	}
}
