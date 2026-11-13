package de.ollie.carp.vtt.restclient.adapter;

import de.ollie.carp.vtt.core.service.model.Scenario;
import de.ollie.carp.vtt.core.service.port.web.ScenarioWebPort;
import de.ollie.carp.vtt.restclient.ScenarioClient;
import jakarta.inject.Named;
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
public class ScenarioWebAdapter implements ScenarioWebPort {

	private final ScenarioClient client;

	@Override
	public void pushScenario(Scenario toPush) {
		client.updateScenario(toPush);
	}

	@Override
	public void deleteScenario(UUID idToDelete) {
		client.removeScenario(idToDelete);
	}
}
