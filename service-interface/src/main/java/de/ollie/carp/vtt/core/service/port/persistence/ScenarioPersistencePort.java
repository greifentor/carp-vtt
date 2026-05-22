package de.ollie.carp.vtt.core.service.port.persistence;

import de.ollie.carp.vtt.core.service.model.Scenario;
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
public interface ScenarioPersistencePort {
	Scenario create(String name);

	void deleteById(UUID id);

	Optional<Scenario> findById(UUID id);

	List<Scenario> list();

	Scenario update(Scenario toSave);
}
