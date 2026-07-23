package de.ollie.carp.vtt.restserver.core.service.model;

import java.util.UUID;
import lombok.Data;
import lombok.Generated;
import lombok.experimental.Accessors;

/**
 * GENERATED CODE - DO NOT TOUCH
 *
 * Remove this comment to suspend class from generation process.
 */
@Accessors(chain = true)
@Data
@Generated
public class TokenPosition {

	private UUID id;
	private UUID battleMapId;
	private int coordinateX;
	private int coordinateY;
	private UUID partyId;
	private UUID scenarioId;
	private UUID tokenId;
}
