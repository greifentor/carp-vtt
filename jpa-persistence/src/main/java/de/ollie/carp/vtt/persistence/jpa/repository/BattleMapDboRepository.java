package de.ollie.carp.vtt.persistence.jpa.repository;

import de.ollie.carp.vtt.persistence.jpa.dbo.BattleMapDbo;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * GENERATED CODE - DO NOT TOUCH
 *
 * Remove this comment to suspend class from generation process.
 */
@Repository
public interface BattleMapDboRepository extends JpaRepository<BattleMapDbo, UUID> {
	@Query("SELECT dbo FROM BattleMapDbo dbo ORDER BY dbo.name")
	List<BattleMapDbo> findAllOrdered();
}
