package de.ollie.carp.vtt.core.service.port.web;

import de.ollie.carp.vtt.core.service.model.DndTokenData;
import java.util.UUID;
import lombok.Generated;

/**
 * GENERATED CODE - DO NOT TOUCH
 *
 * Remove this comment to suspend class from generation process.
 */
@Generated
public interface DndTokenDataWebPort {
	void deleteDndTokenData(UUID idToDelete);

	void pushDndTokenData(DndTokenData toPush);
}
