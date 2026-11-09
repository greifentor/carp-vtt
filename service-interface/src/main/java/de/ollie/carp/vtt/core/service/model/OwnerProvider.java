package de.ollie.carp.vtt.core.service.model;

import java.util.UUID;

public interface OwnerProvider {
	UUID getOwner();

	void setOwner(UUID id);
}
