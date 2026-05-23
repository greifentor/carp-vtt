package de.ollie.carp.vtt.persistence.jpa.dbo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import java.util.UUID;
import lombok.Generated;

/**
 * GENERATED CODE - DO NOT TOUCH
 *
 * Remove this comment to suspend class from generation process.
 */
@Entity
@Generated
@Table(
	name = "TOKEN_MAP_PARTY_SCENARIO",
	uniqueConstraints = {
		@UniqueConstraint(
			name = "uc_token_map_party_scenario_all",
			columnNames = { "map_id", "token_id", "party_id", "scenario_id" }
		),
	}
)
public class TokenMapPartyScenarioDbo {

	@Id
	@Column(name = "id", nullable = false, updatable = false)
	private UUID id;

	@ManyToOne(optional = false)
	@JoinColumn(name = "map_id", nullable = false)
	private MapDbo map;

	@ManyToOne(optional = false)
	@JoinColumn(name = "token_id", nullable = false)
	private TokenDbo token;

	@ManyToOne(optional = false)
	@JoinColumn(name = "party_id", nullable = false)
	private PartyDbo party;

	@ManyToOne(optional = false)
	@JoinColumn(name = "scenario_id", nullable = false)
	private ScenarioDbo scenario;
}
