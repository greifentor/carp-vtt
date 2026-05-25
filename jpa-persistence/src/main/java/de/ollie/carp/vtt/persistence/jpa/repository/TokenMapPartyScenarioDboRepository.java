package de.ollie.carp.vtt.persistence.jpa.repository;

import de.ollie.carp.vtt.persistence.jpa.dbo.MapDbo;
import de.ollie.carp.vtt.persistence.jpa.dbo.PartyDbo;
import de.ollie.carp.vtt.persistence.jpa.dbo.ScenarioDbo;
import de.ollie.carp.vtt.persistence.jpa.dbo.TokenDbo;
import de.ollie.carp.vtt.persistence.jpa.dbo.TokenMapPartyScenarioDbo;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.repository.CrudRepository;

public interface TokenMapPartyScenarioDboRepository extends CrudRepository<TokenMapPartyScenarioDbo, UUID> {
	List<TokenMapPartyScenarioDbo> findAllByAndMapAndPartyAndScenario(MapDbo map, PartyDbo party, ScenarioDbo scenario);

	Optional<TokenMapPartyScenarioDbo> findByTokenAndMapAndPartyAndScenario(
		TokenDbo token,
		MapDbo map,
		PartyDbo party,
		ScenarioDbo scenario
	);
}
