package de.ollie.carp.vtt.core.service.model;

import java.util.UUID;
import lombok.Generated;

/**
 * GENERATED CODE - DO NOT TOUCH
 *
 * Remove this comment to suspend class from generation process.
 */
@Generated
public interface TokenData extends TokenDataInfoProvider {
	Coordinates getCoordinates();
	int getCounter();
	UUID getId();
	boolean isSelected();
	Token getToken();

	void setCoordinates(Coordinates coordinates);
	void setCounter(int counter);
	void setId(UUID id);
	void setSelected(boolean selected);
	void setToken(Token token);
}
