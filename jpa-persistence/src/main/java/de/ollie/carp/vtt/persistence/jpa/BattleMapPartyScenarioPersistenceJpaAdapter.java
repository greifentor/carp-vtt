package de.ollie.carp.vtt.persistence.jpa;

import de.ollie.carp.vtt.core.service.model.BattleMap;
import de.ollie.carp.vtt.core.service.model.BattleMapPartyScenario;
import de.ollie.carp.vtt.core.service.model.Party;
import de.ollie.carp.vtt.core.service.model.Scenario;
import de.ollie.carp.vtt.core.service.port.persistence.BattleMapPartyScenarioPersistencePort;
import de.ollie.carp.vtt.persistence.jpa.mapper.BattleMapPartyScenarioDboMapper;
import de.ollie.carp.vtt.persistence.jpa.repository.BattleMapPartyScenarioDboRepository;
import jakarta.inject.Named;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import lombok.Generated;
import lombok.RequiredArgsConstructor;

/**
 * GENERATED CODE - DO NOT TOUCH
 *
 * Remove this comment to suspend class from generation process.
 */
@Generated
@Named
@RequiredArgsConstructor
public class BattleMapPartyScenarioPersistenceJpaAdapter implements BattleMapPartyScenarioPersistencePort {

	private final BattleMapPartyScenarioDboMapper mapper;
	private final BattleMapPartyScenarioDboRepository repository;

	@Override
	public BattleMapPartyScenario create(BattleMap battleMap, Party party, Scenario scenario) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteById(UUID id) {
		repository.deleteById(id);
	}

	@Override
	public Optional<BattleMapPartyScenario> findById(UUID id) {
		return repository.findById(id).map(mapper::toModel);
	}

	@Override
	public List<BattleMapPartyScenario> list() {
		return mapper.toModels(repository.findAll());
	}

	@Override
	public BattleMapPartyScenario update(BattleMapPartyScenario toSave) {
		return mapper.toModel(repository.save(mapper.toDbo(toSave)));
	}
}
