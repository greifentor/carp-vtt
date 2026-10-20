package de.ollie.carp.vtt.core.service;

import de.ollie.carp.vtt.core.service.model.DndTokenData;
import java.util.Optional;
import java.util.UUID;

public interface DndTokenDataService {
	DndTokenData create(int hitPoints);

	Optional<DndTokenData> findById(UUID id);

	DndTokenData save(DndTokenData toSave);
}
