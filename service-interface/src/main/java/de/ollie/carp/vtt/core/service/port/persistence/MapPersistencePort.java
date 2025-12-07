package de.ollie.carp.vtt.core.service.port.persistence;

import de.ollie.carp.vtt.core.service.model.Map;
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
public interface MapPersistencePort {
	Map create(String name, byte[] image);

	void deleteById(UUID id);

	Optional<Map> findById(UUID id);

	List<Map> list();

	Map update(Map toSave);
}
