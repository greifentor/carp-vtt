package de.ollie.carp.vtt.persistence.jpa.dbo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.math.BigDecimal;
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
@Entity
@Generated
@Table(name = "TOKEN_BATTLE_MAP_PARTY_SCENARIO")
public class TokenMapPartyScenarioDbo {

	@Id
	@Column(name = "ID", nullable = false, updatable = false)
	private UUID id;

	@ManyToOne(optional = false)
	@JoinColumn(name = "BATTLE_MAP_ID", nullable = false)
	private BattleMapDbo battleMap;

	@ManyToOne(optional = false)
	@JoinColumn(name = "TOKEN_ID", nullable = false)
	private TokenDbo token;

	@ManyToOne(optional = false)
	@JoinColumn(name = "PARTY_ID", nullable = false)
	private PartyDbo party;

	@ManyToOne(optional = false)
	@JoinColumn(name = "SCENARIO_ID", nullable = false)
	private ScenarioDbo scenario;

	@Column(name = "FIELD_X", nullable = false)
	private BigDecimal fieldX;

	@Column(name = "FIELD_Y", nullable = false)
	private BigDecimal fieldY;
}
