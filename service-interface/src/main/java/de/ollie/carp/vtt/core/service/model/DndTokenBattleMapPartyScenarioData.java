package de.ollie.carp.vtt.core.service.model;

import java.util.UUID;
import lombok.Generated;

/**
 * GENERATED CODE - DO NOT TOUCH
 *
 * Remove this comment to suspend class from generation process.
 */
@Generated
public interface DndTokenBattleMapPartyScenarioData<T> {
	public UUID getId();

	public int getCurrentHitPoints();

	public int getDeathSavingThrowsFail();

	public int getDeathSavingThrowsSuccess();

	public int getTemporaryHitPoints();

	public T setId(UUID id);

	public T setCurrentHitPoints(int currentHitPoints);

	public T setDeathSavingThrowsFail(int deathSavingThrowsFail);

	public T setDeathSavingThrowsSuccess(int deathSavingThrowsSuccess);

	public T setTemporaryHitPoints(int temporaryHitPoints);
}
