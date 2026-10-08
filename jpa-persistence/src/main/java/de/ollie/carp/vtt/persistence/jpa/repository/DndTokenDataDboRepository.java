package de.ollie.carp.vtt.persistence.jpa.repository;

import de.ollie.carp.vtt.persistence.jpa.dbo.DndTokenDataDbo;
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
public interface DndTokenDataDboRepository extends JpaRepository<DndTokenDataDbo, UUID> {
	@Query("SELECT dbo FROM DndTokenDataDbo dbo")
	List<DndTokenDataDbo> findAllOrdered();
}
