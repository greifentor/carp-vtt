package de.ollie.carp.vtt.persistence.jpa;

import de.ollie.carp.vtt.core.service.model.TokenBattleMapPartyScenario;
import de.ollie.carp.vtt.core.service.port.persistence.TokenBattleMapPartyScenarioPersistencePort;
import de.ollie.carp.vtt.persistence.jpa.mapper.TokenBattleMapPartyScenarioDboMapper;
import de.ollie.carp.vtt.persistence.jpa.repository.TokenBattleMapPartyScenarioDboRepository;
import jakarta.inject.Named;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;

@Named
@RequiredArgsConstructor
public class TokenBattleMapPartyScenarioDataPersistenceJpaAdapter
	implements TokenBattleMapPartyScenarioPersistencePort {

	private final TokenBattleMapPartyScenarioDboMapper mapper;
	private final TokenBattleMapPartyScenarioDboRepository repository;

	@Override
	public TokenBattleMapPartyScenario create(
		UUID battleMapId,
		int counter,
		UUID partyId,
		int fieldX,
		int fieldY,
		UUID scenarioId,
		UUID tokenId
	) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteById(UUID id) {
		repository.deleteById(id);
	}

	@Override
	public Optional<TokenBattleMapPartyScenario> findById(UUID id) {
		return repository.findById(id).map(mapper::toModel);
	}

	@Override
	public Optional<TokenBattleMapPartyScenario> findByBattleMapPartyScenarioToken(
		UUID battleMapId,
		UUID partyId,
		UUID scenarioId,
		UUID tokenId
	) {
		return repository
			.findByBattleMapIdAndPartyIdAndScenarioIdAndTokenId(battleMapId, partyId, scenarioId, tokenId)
			.map(mapper::toModel);
	}

	@Override
	public List<TokenBattleMapPartyScenario> list() {
		return mapper.toModels(repository.findAll());
	}

	@Override
	public TokenBattleMapPartyScenario update(TokenBattleMapPartyScenario toSave) {
		return mapper.toModel(repository.save(mapper.toDbo(toSave)));
	}
}
