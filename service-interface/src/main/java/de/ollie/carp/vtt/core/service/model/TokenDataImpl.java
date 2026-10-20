package de.ollie.carp.vtt.core.service.model;

import java.util.UUID;
import lombok.Data;
import lombok.Generated;
import lombok.experimental.Accessors;

/**
 * GENERATED CODE - DO NOT TOUCH
 *
 * Remove this comment to suspend class from generation process.
 */
@Accessors(chain = true)
@Data
@Generated
public class TokenDataImpl implements TokenData<TokenDataImpl> {

	private Coordinates coordinates;
	private int counter;
	private UUID id;
	private boolean selected;
	private Token token;
}
