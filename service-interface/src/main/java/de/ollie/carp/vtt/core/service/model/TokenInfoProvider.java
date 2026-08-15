package de.ollie.carp.vtt.core.service.model;

import java.util.UUID;

public interface TokenInfoProvider {
	UUID getId();

	byte[] getImage();

	String getName();

	TokenSize getTokenSize();
}
