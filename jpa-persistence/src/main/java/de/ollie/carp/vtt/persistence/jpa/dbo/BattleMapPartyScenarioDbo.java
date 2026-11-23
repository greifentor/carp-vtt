package de.ollie.carp.vtt.persistence.jpa.dbo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Entity(name = "BattleMapPartyScenarioDbo")
@Table(name = "BATTLE_MAP_PARTY_SCENARIO")
public class BattleMapPartyScenarioDbo {

	@JoinColumn(name = "BATTLE_MAP", referencedColumnName = "ID", nullable = false)
	@ManyToOne(fetch = FetchType.EAGER)
	private BattleMapDbo battleMap;

	@JoinColumn(name = "PARTY", referencedColumnName = "ID", nullable = false)
	@ManyToOne(fetch = FetchType.EAGER)
	private PartyDbo party;

	@JoinColumn(name = "SCENARIO", referencedColumnName = "ID", nullable = false)
	@ManyToOne(fetch = FetchType.EAGER)
	private ScenarioDbo scenario;

	@Id
	@Column(name = "ID", nullable = false)
	private UUID id;
}
