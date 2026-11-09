package de.ollie.carp.vtt.core.service.model;

import java.util.UUID;
import lombok.Generated;

/**
 * GENERATED CODE - DO NOT TOUCH
 *
 * Remove this comment to suspend class from generation process.
 */
@Generated
public interface TokenBattleMapPartyScenario<T> {
	public UUID getBattleMapId();

	public int getCounter();

	public int getFieldX();

	public int getFieldY();

	public UUID getId();

	public UUID getPartyId();

	public UUID getScenarioId();

	public UUID getTokenId();

	public T setBattleMapId(UUID battleMapId);

	public T setCounter(int counter);

	public T setFieldX(int fieldX);

	public T setFieldY(int fieldY);

	public T setId(UUID id);

	public T setPartyId(UUID partyId);

	public T setScenarioId(UUID scenarioId);

	public T setTokenId(UUID tokenId);
}
