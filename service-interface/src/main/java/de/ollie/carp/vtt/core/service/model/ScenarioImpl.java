package de.ollie.carp.vtt.core.service.model;

import java.util.UUID;
import lombok.Data;
import lombok.Generated;

/**
 * GENERATED CODE - DO NOT TOUCH
 *
 * Remove this comment to suspend class from generation process.
 */
@Data
@Generated
public class ScenarioImpl implements Scenario {

	private UUID id = UUID.randomUUID();
	private String name;
	private UUID owner;
}
