package de.ollie.carp.vtt.core.service.port.persistence;

import de.ollie.carp.vtt.core.service.model.BattleMap;
import de.ollie.carp.vtt.core.service.model.BattleMapPartyScenario;
import de.ollie.carp.vtt.core.service.model.Party;
import de.ollie.carp.vtt.core.service.model.Scenario;
import jakarta.inject.Named;
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
@Named
public interface BattleMapPartyScenarioPersistencePort {
	BattleMapPartyScenario create(BattleMap battleMap, Party party, Scenario scenario);

	void deleteById(UUID id);

	Optional<BattleMapPartyScenario> findById(UUID id);

	List<BattleMapPartyScenario> list();

	BattleMapPartyScenario update(BattleMapPartyScenario toSave);
}
