package de.ollie.carp.vtt.core.service.model.event;

import de.ollie.carp.vtt.core.service.model.BattleMap;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@AllArgsConstructor
@EqualsAndHashCode
@Getter
public class BattleMapUpdateEvent {

	private UUID id;

	private BattleMap battleMap;
}
