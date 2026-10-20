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
@Entity(name = "DndTokenBattleMapPartyScenarioDataDbo")
@Table(name = "DND_TOKEN_BATTLE_MAP_PARTY_SCENARIO_DATA")
public class DndTokenBattleMapPartyScenarioDataDbo {

	@Id
	@Column(name = "ID", nullable = false)
	private UUID id;

	@Column(name = "CURRENT_HIT_POINTS", nullable = false)
	private int currentHitPoints;

	@Column(name = "DEATH_SAVING_THROWS_FAIL", nullable = false)
	private int deathSavingThrowsFail;

	@Column(name = "DEATH_SAVING_THROWS_SUCCESS", nullable = false)
	private int deathSavingThrowsSuccess;

	@Column(name = "TEMPORARY_HIT_POINTS", nullable = false)
	private int temporaryHitPoints;
}
