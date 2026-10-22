package de.ollie.carp.vtt.core.service.model;

import java.util.UUID;
import lombok.Data;
import lombok.Generated;
import lombok.experimental.Accessors;

/**
 * GENERATED CODE - DO NOT TOUCH
 *
 * Remove this comment to suspend class from generation process.
 */
@Accessors(chain = true)
@Data
@Generated
public class TokenBattleMapPartyScenarioImpl implements TokenBattleMapPartyScenario<TokenBattleMapPartyScenarioImpl> {

	private UUID id;
	private UUID battleMapId;
	private int counter;
	private UUID partyId;
	private int fieldX;
	private int fieldY;
	private UUID scenarioId;
	private UUID tokenId;
}
