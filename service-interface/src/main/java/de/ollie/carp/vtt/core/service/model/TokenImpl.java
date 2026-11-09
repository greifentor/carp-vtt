package de.ollie.carp.vtt.core.service.model;

import java.util.UUID;
import lombok.Data;
import lombok.Generated;
import lombok.ToString.Exclude;

/**
 * GENERATED CODE - DO NOT TOUCH
 *
 * Remove this comment to suspend class from generation process.
 */
@Data
@Generated
public class TokenImpl implements Token {

	private UUID id = UUID.randomUUID();

	@Exclude
	private byte[] image;

	private String name;
	private TokenSize tokenSize;
}
