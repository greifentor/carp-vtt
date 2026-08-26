package de.ollie.carp.vtt.core.service.model;

import java.math.BigDecimal;
import java.util.UUID;
import lombok.Data;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Data
public class TokenMapPartyScenario {

	private UUID id;
	private BattleMap battleMap;
	private int counter;
	private Token token;
	private Party party;
	private Scenario scenario;
	private BigDecimal fieldX;
	private BigDecimal fieldY;
}
