package de.ollie.carp.vtt.core.service.port.persistence;

import de.ollie.carp.vtt.core.service.model.BattleMapPartyScenario;
import java.util.List;

public interface BattleMapPartyScenarioPersistencePort {
	List<BattleMapPartyScenario> findAll();
}
