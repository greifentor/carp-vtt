package de.ollie.carp.vtt.service.impl;

import static de.ollie.baselib.util.Check.ensure;

import de.ollie.carp.vtt.core.service.MapService;
import de.ollie.carp.vtt.core.service.model.Map;
import de.ollie.carp.vtt.core.service.port.persistence.MapPersistencePort;
import jakarta.inject.Named;
import lombok.Generated;
import lombok.RequiredArgsConstructor;

/**
 * GENERATED CODE - DO NOT TOUCH
 *
 * Remove this comment to suspend class from generation process.
 */
@Generated
@Named
@RequiredArgsConstructor
class MapServiceImpl implements MapService {

	private final MapPersistencePort persistencePort;

	@Override
	public Map save(Map toSave) {
		ensure(toSave != null, "map cannot be null!");
		return persistencePort.update(toSave);
	}
}
