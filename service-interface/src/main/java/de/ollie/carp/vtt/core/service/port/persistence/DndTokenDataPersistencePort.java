package de.ollie.carp.vtt.core.service.port.persistence;

import de.ollie.carp.vtt.core.service.model.DndTokenData;
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
public interface DndTokenDataPersistencePort {
	DndTokenData create(int hitPoints);

	void deleteById(UUID id);

	Optional<DndTokenData> findById(UUID id);

	List<DndTokenData> list();

	DndTokenData update(DndTokenData toSave);
}
