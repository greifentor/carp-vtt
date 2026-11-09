package de.ollie.carp.vtt.core.service.model;

import java.util.UUID;
import lombok.Generated;

/**
 * GENERATED CODE - DO NOT TOUCH
 *
 * Remove this comment to suspend class from generation process.
 */
@Generated
public interface TokenBattleMapPartyScenario {
	UUID getBattleMapId();
	int getCounter();
	int getFieldX();
	int getFieldY();
	UUID getId();
	UUID getPartyId();
	UUID getScenarioId();
	UUID getTokenId();

	void setBattleMapId(UUID battleMapId);
	void setCounter(int counter);
	void setFieldX(int fieldX);
	void setFieldY(int fieldY);
	void setId(UUID id);
	void setPartyId(UUID partyId);
	void setScenarioId(UUID scenarioId);
	void setTokenId(UUID tokenId);
}
