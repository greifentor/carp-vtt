package de.ollie.service.model;

import lombok.Data;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Data
public class BattleMap {

	private BattleMapId id;
	private String name;
}
