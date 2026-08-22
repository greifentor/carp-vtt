package de.ollie.carp.vtt.restserver.persistence.jpa.repository;

import de.ollie.carp.vtt.restserver.persistence.jpa.dbo.TokenPositionDbo;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExtendedTokenPositionRepository extends JpaRepository<TokenPositionDbo, UUID> {
	List<TokenPositionDbo> findAllByBattleMapIdAndPartyIdAndScenarioId(UUID battleMapId, UUID partyId, UUID scenarioId);
}
