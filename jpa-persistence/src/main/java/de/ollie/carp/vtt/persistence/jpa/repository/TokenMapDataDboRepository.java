package de.ollie.carp.vtt.persistence.jpa.repository;

import de.ollie.carp.vtt.persistence.jpa.dbo.TokenMapDataDbo;
import java.math.BigDecimal;
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
public interface TokenMapDataDboRepository extends JpaRepository<TokenMapDataDbo, UUID> {
	@Query("SELECT dbo FROM TokenMapDataDbo dbo")
	List<TokenMapDataDbo> findAllOrdered();
}
