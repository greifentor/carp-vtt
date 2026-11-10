package de.ollie.carp.vtt.core.service;

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
public interface ScenarioService {
	Scenario createScenario(String name, UUID owner);

	void deleteScenario(UUID id);

	Optional<Scenario> findById(UUID id);

	List<Scenario> listScenarios();

	Scenario updateScenario(Scenario toSave);

	void updateAndPushScenario(Scenario toSave);

	Scenario pushScenario(Scenario toPush);

	void pushDeleteScenario(UUID idToDelete);

	void deleteAndPushDeleteScenario(UUID idToDelete);
}
