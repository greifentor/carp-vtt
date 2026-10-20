package de.ollie.carp.vtt.core.service.model;

import java.util.UUID;
import lombok.Generated;

/**
 * GENERATED CODE - DO NOT TOUCH
 *
 * Remove this comment to suspend class from generation process.
 */
@Generated
public interface TokenData<T> extends TokenDataInfoProvider {
	public Coordinates getCoordinates();

	public int getCounter();

	public UUID getId();

	public boolean isSelected();

	public Token getToken();

	public T setCoordinates(Coordinates coordinates);

	public T setCounter(int counter);

	public T setId(UUID id);

	public T setSelected(boolean selected);

	public T setToken(Token token);
}
