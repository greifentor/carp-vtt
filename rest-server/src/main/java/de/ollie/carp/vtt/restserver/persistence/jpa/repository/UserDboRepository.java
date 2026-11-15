package de.ollie.carp.vtt.restserver.persistence.jpa.repository;

import de.ollie.carp.vtt.restserver.persistence.jpa.dbo.UserDbo;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDboRepository extends JpaRepository<UserDbo, UUID> {}
