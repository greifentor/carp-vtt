package de.ollie.carp.vtt.restclient.adapter;

import de.ollie.carp.vtt.core.service.model.Scenario;
import de.ollie.carp.vtt.core.service.port.web.ScenarioWebPort;
import jakarta.inject.Named;
import java.util.UUID;
import lombok.Generated;

/**
 * GENERATED CODE - DO NOT TOUCH
 *
 * Remove this comment to suspend class from generation process.
 */
@Generated
@Named
public class ScenarioWebAdapter implements ScenarioWebPort {

	@Override
	public Scenario pushScenario(Scenario toPush) {
		System.out.println("rest-push " + toPush);
		return null;
	}

	@Override
	public void deleteScenario(UUID idToDelete) {
		System.out.println("rest-delete " + idToDelete);
	}
}
