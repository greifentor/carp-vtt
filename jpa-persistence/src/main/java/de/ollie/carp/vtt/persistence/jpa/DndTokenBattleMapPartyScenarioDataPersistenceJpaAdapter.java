package de.ollie.carp.vtt.persistence.jpa;

import de.ollie.carp.vtt.core.service.model.DndTokenBattleMapPartyScenarioData;
import de.ollie.carp.vtt.core.service.port.persistence.DndTokenBattleMapPartyScenarioDataPersistencePort;
import de.ollie.carp.vtt.persistence.jpa.mapper.DndTokenBattleMapPartyScenarioDataDboMapper;
import de.ollie.carp.vtt.persistence.jpa.repository.DndTokenBattleMapPartyScenarioDataDboRepository;
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
public class DndTokenBattleMapPartyScenarioDataPersistenceJpaAdapter
	implements DndTokenBattleMapPartyScenarioDataPersistencePort {

	private final DndTokenBattleMapPartyScenarioDataDboMapper mapper;
	private final DndTokenBattleMapPartyScenarioDataDboRepository repository;

	@Override
	public DndTokenBattleMapPartyScenarioData create(
		int currentHitPoints,
		int deathSavingThrowsFail,
		int deathSavingThrowsSuccess,
		int temporaryHitPoints
	) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteById(UUID id) {
		repository.deleteById(id);
	}

	@Override
	public Optional<DndTokenBattleMapPartyScenarioData> findById(UUID id) {
		return repository.findById(id).map(mapper::toModel);
	}

	@Override
	public List<DndTokenBattleMapPartyScenarioData> list() {
		return mapper.toModels(repository.findAll());
	}

	@Override
	public DndTokenBattleMapPartyScenarioData update(DndTokenBattleMapPartyScenarioData toSave) {
		return mapper.toModel(repository.save(mapper.toDbo(toSave)));
	}
}
