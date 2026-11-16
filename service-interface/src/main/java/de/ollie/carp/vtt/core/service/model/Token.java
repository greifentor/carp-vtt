package de.ollie.carp.vtt.core.service.model;

import java.util.UUID;
import lombok.Generated;

/**
 * GENERATED CODE - DO NOT TOUCH
 *
 * Remove this comment to suspend class from generation process.
 */
@Generated
public interface Token extends OwnerProvider, TokenInfoProvider {
	UUID getId();
	byte[] getImage();
	String getName();
	UUID getOwner();
	TokenSize getTokenSize();

	void setId(UUID id);
	void setImage(byte[] image);
	void setName(String name);
	void setOwner(UUID owner);
	void setTokenSize(TokenSize tokenSize);
}
