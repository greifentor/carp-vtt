package de.ollie.carp.vtt.core.service.impl;

import de.ollie.carp.vtt.core.service.BattleMapPartyScenarioService;
import de.ollie.carp.vtt.core.service.model.BattleMapPartyScenario;
import de.ollie.carp.vtt.core.service.port.persistence.BattleMapPartyScenarioPersistencePort;
import jakarta.inject.Named;
import java.util.List;
import lombok.RequiredArgsConstructor;

@Named
@RequiredArgsConstructor
class BattleMapPartyScenarioServiceImpl implements BattleMapPartyScenarioService {

	private final BattleMapPartyScenarioPersistencePort battleMapPartyScenarioPersistencePort;

	@Override
	public List<BattleMapPartyScenario> findAll() {
		return battleMapPartyScenarioPersistencePort.findAll();
	}
}
