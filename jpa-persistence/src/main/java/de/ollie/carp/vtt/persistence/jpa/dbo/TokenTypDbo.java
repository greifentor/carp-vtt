package de.ollie.carp.vtt.persistence.jpa.dbo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
@Entity(name = "TokenTypDbo")
@Table(name = "TOKEN_TYP")
public class TokenTypDbo {

	@Id
	@Column(name = "ID", nullable = false)
	private UUID id;

	@Column(name = "NAME", nullable = false)
	private String name;

	@Column(name = "TOKEN", nullable = false)
	private byte[] token;
}
