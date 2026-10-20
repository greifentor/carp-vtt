package de.ollie.carp.vtt.core.service.model;

import java.util.UUID;
import lombok.Generated;

/**
 * GENERATED CODE - DO NOT TOUCH
 *
 * Remove this comment to suspend class from generation process.
 */
@Generated
public interface DndTokenData<T> {
	public UUID getId();

	public int getHitPoints();

	public T setId(UUID id);

	public T setHitPoints(int hitPoints);
}
