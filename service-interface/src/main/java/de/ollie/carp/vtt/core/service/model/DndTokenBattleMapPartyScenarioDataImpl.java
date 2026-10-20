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
public class DndTokenBattleMapPartyScenarioDataImpl
	implements DndTokenBattleMapPartyScenarioData<DndTokenBattleMapPartyScenarioDataImpl> {

	private UUID id;
	private int currentHitPoints;
	private int deathSavingThrowsFail;
	private int deathSavingThrowsSuccess;
	private int temporaryHitPoints;
}
