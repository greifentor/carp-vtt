package de.ollie.carp.vtt.core.service.port.web;

import de.ollie.carp.vtt.core.service.model.Scenario;
import java.util.UUID;
import lombok.Generated;

/**
 * GENERATED CODE - DO NOT TOUCH
 *
 * Remove this comment to suspend class from generation process.
 */
@Generated
public interface ScenarioWebPort {
	void pushScenario(Scenario toPush);

	void deleteScenario(UUID idToDelete);
}
