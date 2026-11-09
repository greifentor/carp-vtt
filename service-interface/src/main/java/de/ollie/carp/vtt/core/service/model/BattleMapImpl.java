package de.ollie.carp.vtt.core.service.model;

import java.util.UUID;
import lombok.Data;
import lombok.Generated;
import lombok.ToString.Exclude;

/**
 * GENERATED CODE - DO NOT TOUCH
 *
 * Remove this comment to suspend class from generation process.
 */
@Data
@Generated
public class BattleMapImpl implements BattleMap {

	private UUID id;

	@Exclude
	private byte[] imageContent;

	private String name;
}
