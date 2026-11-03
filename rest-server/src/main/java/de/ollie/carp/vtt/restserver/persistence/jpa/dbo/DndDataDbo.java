package de.ollie.carp.vtt.restserver.persistence.jpa.dbo;

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
@Entity(name = "DndDataDbo")
@Table(name = "DND_DATA")
public class DndDataDbo {

	@Id
	@Column(name = "ID", nullable = false)
	private UUID id;

	@Column(name = "CURRENT_HIT_POINTS", nullable = false)
	private int currentHitPoints;

	@Column(name = "MAX_HIT_POINTS", nullable = true)
	private int maxHitPoints;
}
