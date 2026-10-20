package de.ollie.carp.vtt.migration.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Data
public class DndDataDTO {

	private int maxHitpoints;
}
