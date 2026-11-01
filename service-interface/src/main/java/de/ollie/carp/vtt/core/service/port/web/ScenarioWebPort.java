package de.ollie.carp.vtt.core.service.port.web;

import de.ollie.carp.vtt.core.service.model.Scenario;
import java.util.UUID;

public interface ScenarioWebPort {
	Scenario pushScenario(Scenario toPush);

	void deleteScenario(UUID idToDelete);
}
