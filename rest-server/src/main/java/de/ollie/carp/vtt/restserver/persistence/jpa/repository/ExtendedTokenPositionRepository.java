package de.ollie.carp.vtt.restserver.persistence.jpa.repository;

import de.ollie.carp.vtt.restserver.persistence.jpa.dbo.TokenPositionDbo;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ExtendedTokenPositionRepository extends JpaRepository<TokenPositionDbo, UUID> {
	List<TokenPositionDbo> findAllByBattleMapIdAndPartyIdAndScenarioId(UUID battleMapId, UUID partyId, UUID scenarioId);

	TokenPositionDbo findByBattleMapIdAndPartyIdAndScenarioIdAndSelectedIsTrue(
		UUID battleMapId,
		UUID partyId,
		UUID scenarioId
	);

	@Transactional
	@Modifying
	@Query(
		"UPDATE TokenPositionDbo dbo " +
		"SET dbo.selected = false " +
		"WHERE dbo.battleMapId = :battleMapId " +
		"AND dbo.partyId = :partyId " +
		"AND dbo.scenarioId = :scenarioId"
	)
	void resetSelectedForBattleMapPartyAndScenario(UUID battleMapId, UUID partyId, UUID scenarioId);
}
