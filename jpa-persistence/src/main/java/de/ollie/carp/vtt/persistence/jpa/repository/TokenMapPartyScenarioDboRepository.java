package de.ollie.carp.vtt.persistence.jpa.repository;

import de.ollie.carp.vtt.persistence.jpa.dbo.TokenMapPartyScenarioDbo;
import java.util.UUID;
import org.springframework.data.repository.CrudRepository;

public interface TokenMapPartyScenarioDboRepository extends CrudRepository<TokenMapPartyScenarioDbo, UUID> {}
