package de.ollie.carp.vtt.persistence.jpa.repository;

import de.ollie.carp.vtt.persistence.jpa.dbo.BattleMapPartyScenarioResult;
import de.ollie.carp.vtt.persistence.jpa.dbo.TokenBattleMapPartyScenarioDbo;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BattleMapPartyScenarioRepository extends JpaRepository<TokenBattleMapPartyScenarioDbo, UUID> {
	@Query(
		"select distinct new de.ollie.carp.vtt.persistence.jpa.dbo.BattleMapPartyScenarioResult(dbo.battleMapId, dbo.partyId, dbo.scenarioId) from TokenBattleMapPartyScenarioDbo dbo"
	)
	List<BattleMapPartyScenarioResult> findAllBattleMapPartyScenarios();
}
