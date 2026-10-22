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
	public UUID getId();

	public UUID getBattleMapId();

	public int getCounter();

	public UUID getPartyId();

	public int getFieldX();

	public int getFieldY();

	public UUID getScenarioId();

	public UUID getTokenId();

	public T setId(UUID id);

	public T setBattleMapId(UUID battleMapId);

	public T setCounter(int counter);

	public T setPartyId(UUID partyId);

	public T setFieldX(int fieldX);

	public T setFieldY(int fieldY);

	public T setScenarioId(UUID scenarioId);

	public T setTokenId(UUID tokenId);
}
