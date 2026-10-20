package de.ollie.carp.vtt.core.service.model;

import java.util.UUID;
import lombok.Generated;

/**
 * GENERATED CODE - DO NOT TOUCH
 *
 * Remove this comment to suspend class from generation process.
 */
@Generated
public interface BattleMap<T> {
	public UUID getId();

	public String getName();

	public byte[] getImageContent();

	public T setId(UUID id);

	public T setName(String name);

	public T setImageContent(byte[] imageContent);
}
