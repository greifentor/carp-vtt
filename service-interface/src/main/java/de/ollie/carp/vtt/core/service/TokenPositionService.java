package de.ollie.carp.vtt.core.service;

import de.ollie.carp.vtt.core.service.model.BattleMap;
import de.ollie.carp.vtt.core.service.model.Party;
import de.ollie.carp.vtt.core.service.model.Scenario;
import de.ollie.carp.vtt.core.service.model.TokenData;
import de.ollie.carp.vtt.core.service.model.event.TokenPositionUpdateEvent;
import java.util.List;

public interface TokenPositionService {
	List<TokenData> findAllBy(BattleMap battleMap, Party party, Scenario scenario);

	void updateTokenPosition(TokenPositionUpdateEvent tokenPositionUpdateEvent);
}
