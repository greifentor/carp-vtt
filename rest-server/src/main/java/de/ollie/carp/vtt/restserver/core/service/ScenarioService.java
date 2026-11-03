package de.ollie.carp.vtt.restserver.core.service;

import de.ollie.carp.vtt.restserver.core.service.model.Scenario;
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
	Scenario createScenario(String name);

	void deleteScenario(UUID id);

	Optional<Scenario> findById(UUID id);

	List<Scenario> listScenarios();

	Scenario updateScenario(Scenario toSave);
}
