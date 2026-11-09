package de.ollie.carp.vtt.core.service.model;

import java.util.UUID;
import lombok.Data;
import lombok.Generated;

/**
 * GENERATED CODE - DO NOT TOUCH
 *
 * Remove this comment to suspend class from generation process.
 */
@Data
@Generated
public class TokenTypImpl implements TokenTyp {

	private UUID id;
	private String name;
	private byte[] token;
}
