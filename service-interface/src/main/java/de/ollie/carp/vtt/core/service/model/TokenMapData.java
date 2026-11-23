package de.ollie.carp.vtt.core.service.model;

import java.math.BigDecimal;
import java.util.UUID;
import lombok.Generated;

/**
 * GENERATED CODE - DO NOT TOUCH
 *
 * Remove this comment to suspend class from generation process.
 */
@Generated
public interface TokenMapData {
	BattleMapPartyScenario getBattleMapPartyScenario();
	Token getToken();
	int getCounter();
	BigDecimal getFieldX();
	BigDecimal getFieldY();
	UUID getId();

	void setBattleMapPartyScenario(BattleMapPartyScenario battleMapPartyScenario);
	void setToken(Token token);
	void setCounter(int counter);
	void setFieldX(BigDecimal fieldX);
	void setFieldY(BigDecimal fieldY);
	void setId(UUID id);
}
