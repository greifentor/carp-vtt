package de.ollie.carp.vtt.core.service.model;

import java.util.UUID;
import lombok.Generated;

/**
 * GENERATED CODE - DO NOT TOUCH
 *
 * Remove this comment to suspend class from generation process.
 */
@Generated
public interface BattleMap extends OwnerProvider {
	UUID getId();
	byte[] getImageContent();
	String getName();
	UUID getOwner();

	void setId(UUID id);
	void setImageContent(byte[] imageContent);
	void setName(String name);
	void setOwner(UUID owner);
}
