package de.ollie.carp.vtt.service.impl;

import de.ollie.carp.vtt.core.service.UuidService;
import jakarta.inject.Named;
import java.util.UUID;

@Named
class UuidServiceImpl implements UuidService {

	@Override
	public UUID create() {
		return UUID.randomUUID();
	}
}
