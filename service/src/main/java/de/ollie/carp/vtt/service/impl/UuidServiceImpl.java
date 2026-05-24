package de.ollie.carp.vtt.service.impl;

import de.ollie.carp.vtt.core.service.UuidService;
import java.util.UUID;

public class UuidServiceImpl implements UuidService {

	@Override
	public UUID create() {
		return UUID.randomUUID();
	}
}
