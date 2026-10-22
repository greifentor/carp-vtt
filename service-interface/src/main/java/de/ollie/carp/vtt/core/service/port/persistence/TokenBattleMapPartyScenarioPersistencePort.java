package de.ollie.carp.vtt.core.service.port.persistence;

import de.ollie.carp.vtt.core.service.model.TokenBattleMapPartyScenario;
import jakarta.inject.Named;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Named
public interface TokenBattleMapPartyScenarioPersistencePort {
	TokenBattleMapPartyScenario create(
		UUID battleMapId,
		int counter,
		UUID partyId,
		int fieldX,
		int fieldY,
		UUID scenarioId,
		UUID tokenId
	);

	void deleteById(UUID id);

	Optional<TokenBattleMapPartyScenario> findById(UUID id);

	Optional<TokenBattleMapPartyScenario> findByBattleMapPartyScenarioToken(
		UUID battleMapId,
		UUID partyId,
		UUID scenarioId,
		UUID tokenId
	);

	List<TokenBattleMapPartyScenario> list();

	TokenBattleMapPartyScenario update(TokenBattleMapPartyScenario toSave);
}
