package de.ollie.carp.vtt.persistence.jpa.repository;

import de.ollie.carp.vtt.persistence.jpa.dbo.TokenBattleMapPartyScenarioDbo;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenBattleMapPartyScenarioDboRepository extends JpaRepository<TokenBattleMapPartyScenarioDbo, UUID> {
	@Query("SELECT dbo FROM TokenBattleMapPartyScenarioDbo dbo")
	List<TokenBattleMapPartyScenarioDbo> findAllOrdered();

	Optional<TokenBattleMapPartyScenarioDbo> findByBattleMapIdAndPartyIdAndScenarioIdAndTokenId(
		UUID battleMapId,
		UUID partyId,
		UUID scenarioId,
		UUID tokenId
	);
}
