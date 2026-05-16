package de.ollie.carp.vtt.persistence.jpa.repository;

import de.ollie.carp.vtt.persistence.jpa.dbo.TokenTypDbo;
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
public interface TokenTypDboRepository extends JpaRepository<TokenTypDbo, UUID> {
	@Query("SELECT dbo FROM TokenTypDbo dbo ORDER BY dbo.name")
	List<TokenTypDbo> findAllOrdered();

	@Query("SELECT dbo FROM TokenTypDbo dbo WHERE dbo.name LIKE CONCAT('%', :name, '%')")
	List<TokenTypDbo> findAllByNameMatch(String name);
}
