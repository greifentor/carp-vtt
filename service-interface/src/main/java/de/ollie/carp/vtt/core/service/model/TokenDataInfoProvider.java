package de.ollie.carp.vtt.core.service.model;

import java.util.UUID;

public interface TokenDataInfoProvider {
	CoordinatesInfoProvider getCoordinates();

	UUID getId();

	TokenInfoProvider getToken();
}
