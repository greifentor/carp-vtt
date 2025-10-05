package de.ollie.vtt.core.service.port.persistence;

import de.ollie.vtt.core.service.model.BattleMap;
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
public interface BattleMapPersistencePort {
	BattleMap create(String name, byte[] mapImage);

	void deleteById(UUID id);

	Optional<BattleMap> findById(UUID id);

	List<BattleMap> list();

	BattleMap update(BattleMap toSave);
}
