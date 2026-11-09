package de.ollie.carp.vtt.core.service.model;

import java.util.UUID;
import lombok.Generated;

/**
 * GENERATED CODE - DO NOT TOUCH
 *
 * Remove this comment to suspend class from generation process.
 */
@Generated
public interface DndTokenBattleMapPartyScenarioData {
	int getCurrentHitPoints();
	int getDeathSavingThrowsFail();
	int getDeathSavingThrowsSuccess();
	UUID getId();
	int getTemporaryHitPoints();

	void setCurrentHitPoints(int currentHitPoints);
	void setDeathSavingThrowsFail(int deathSavingThrowsFail);
	void setDeathSavingThrowsSuccess(int deathSavingThrowsSuccess);
	void setId(UUID id);
	void setTemporaryHitPoints(int temporaryHitPoints);
}
