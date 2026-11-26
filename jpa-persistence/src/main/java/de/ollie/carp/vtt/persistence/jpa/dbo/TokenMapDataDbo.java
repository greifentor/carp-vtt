package de.ollie.carp.vtt.persistence.jpa.dbo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
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
@Generated
@Entity(name = "TokenMapDataDbo")
@Table(name = "TOKEN_MAP_DATA")
public class TokenMapDataDbo {

	@JoinColumn(name = "BATTLE_MAP_PARTY_SCENARIO", referencedColumnName = "ID", nullable = false)
	@ManyToOne(fetch = FetchType.EAGER)
	private BattleMapPartyScenarioDbo battleMapPartyScenario;

	@JoinColumn(name = "TOKEN", referencedColumnName = "ID", nullable = false)
	@ManyToOne(fetch = FetchType.EAGER)
	private TokenDbo token;

	@Column(name = "COUNTER", nullable = false)
	private int counter;

	@Column(name = "FIELD_X", nullable = false)
	private BigDecimal fieldX;

	@Column(name = "FIELD_Y", nullable = false)
	private BigDecimal fieldY;

	@Column(name = "SELECTED", nullable = false)
	private boolean selected;

	@Id
	@Column(name = "ID", nullable = false)
	private UUID id;
}
