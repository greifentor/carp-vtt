package de.ollie.carp.vtt.core.service.model;

import java.util.UUID;
import lombok.Generated;

/**
 * GENERATED CODE - DO NOT TOUCH
 *
 * Remove this comment to suspend class from generation process.
 */
@Generated
public interface Token<T> extends TokenInfoProvider {
	public UUID getId();

	public byte[] getImage();

	public String getName();

	public TokenSize getTokenSize();

	public T setId(UUID id);

	public T setImage(byte[] image);

	public T setName(String name);

	public T setTokenSize(TokenSize tokenSize);
}
