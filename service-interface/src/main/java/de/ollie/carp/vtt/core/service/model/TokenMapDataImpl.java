package de.ollie.carp.vtt.core.service.model;

import java.math.BigDecimal;
import java.util.UUID;
import lombok.Data;
import lombok.Generated;

/**
 * GENERATED CODE - DO NOT TOUCH
 *
 * Remove this comment to suspend class from generation process.
 */
@Data
@Generated
public class TokenMapDataImpl implements TokenMapData {

	private BattleMapPartyScenario battleMapPartyScenario;
	private Token token;
	private int counter;
	private BigDecimal fieldX;
	private BigDecimal fieldY;
	private boolean selected;
	private UUID id;
}
