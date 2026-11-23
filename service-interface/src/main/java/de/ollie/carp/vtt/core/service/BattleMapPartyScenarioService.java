package de.ollie.carp.vtt.core.service;

import de.ollie.carp.vtt.core.service.model.BattleMap;
import de.ollie.carp.vtt.core.service.model.BattleMapPartyScenario;
import de.ollie.carp.vtt.core.service.model.Party;
import de.ollie.carp.vtt.core.service.model.Scenario;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import lombok.Generated;

/**
 * GENERATED CODE - DO NOT TOUCH
 *
 * Remove this comment to suspend class from generation process.
 */
@Generated
public interface BattleMapPartyScenarioService {
	BattleMapPartyScenario createBattleMapPartyScenario(BattleMap battleMap, Party party, Scenario scenario);

	void deleteBattleMapPartyScenario(UUID id);

	Optional<BattleMapPartyScenario> findById(UUID id);

	List<BattleMapPartyScenario> listBattleMapPartyScenarios();

	BattleMapPartyScenario updateBattleMapPartyScenario(BattleMapPartyScenario toSave);
}
