package de.ollie.carp.vtt.persistence.jpa.dbo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
@Entity(name = "TokenBattleMapPartyScenarioDbo")
@Table(name = "TOKEN_BATTLE_MAP_PARTY_SCENARIO")
public class TokenBattleMapPartyScenarioDbo {

	@Id
	@Column(name = "ID", nullable = false)
	private UUID id;

	@Column(name = "BATTLE_MAP_ID", nullable = false)
	private UUID battleMapId;

	@Column(name = "COUNTER", nullable = false)
	private int counter;

	@Column(name = "PARTY_ID", nullable = false)
	private UUID partyId;

	@Column(name = "FIELD_X", nullable = false)
	private int fieldX;

	@Column(name = "FIELD_Y", nullable = false)
	private int fieldY;

	@Column(name = "SCENARIO_ID", nullable = false)
	private UUID scenarioId;

	@Column(name = "TOKEN_ID", nullable = false)
	private UUID tokenId;
}
