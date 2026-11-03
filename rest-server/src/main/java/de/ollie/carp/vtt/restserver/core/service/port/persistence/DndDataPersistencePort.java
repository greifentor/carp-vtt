package de.ollie.carp.vtt.restserver.core.service.port.persistence;

import de.ollie.carp.vtt.restserver.core.service.model.DndData;
import jakarta.inject.Named;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import lombok.Generated;

/**
 * GENERATED CODE - DO NOT TOUCH
 *
 * Remove this comment to suspend class from generation process.
 */
@Generated
@Named
public interface DndDataPersistencePort {
	DndData create(int currentHitPoints, int maxHitPoints);

	void deleteById(UUID id);

	Optional<DndData> findById(UUID id);

	List<DndData> list();

	DndData update(DndData toSave);
}
