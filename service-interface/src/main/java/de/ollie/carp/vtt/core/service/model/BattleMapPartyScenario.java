package de.ollie.carp.vtt.core.service.model;

import java.util.UUID;
import lombok.Generated;

/**
 * GENERATED CODE - DO NOT TOUCH
 *
 * Remove this comment to suspend class from generation process.
 */
@Generated
public interface BattleMapPartyScenario {
	BattleMap getBattleMap();
	Party getParty();
	Scenario getScenario();
	UUID getId();

	void setBattleMap(BattleMap battleMap);
	void setParty(Party party);
	void setScenario(Scenario scenario);
	void setId(UUID id);
}
