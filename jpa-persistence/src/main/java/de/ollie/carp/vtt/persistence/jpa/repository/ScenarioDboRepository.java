package de.ollie.carp.vtt.persistence.jpa.repository;

import de.ollie.carp.vtt.persistence.jpa.dbo.ScenarioDbo;
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
public interface ScenarioDboRepository extends JpaRepository<ScenarioDbo, UUID> {
	@Query("SELECT dbo FROM ScenarioDbo dbo ORDER BY dbo.name")
	List<ScenarioDbo> findAllOrdered();
}
