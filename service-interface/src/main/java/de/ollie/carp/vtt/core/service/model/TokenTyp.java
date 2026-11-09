package de.ollie.carp.vtt.core.service.model;

import java.util.UUID;
import lombok.Generated;

/**
 * GENERATED CODE - DO NOT TOUCH
 *
 * Remove this comment to suspend class from generation process.
 */
@Generated
public interface TokenTyp {
	UUID getId();
	String getName();
	byte[] getToken();

	void setId(UUID id);
	void setName(String name);
	void setToken(byte[] token);
}
