package de.ollie.carp.vtt.persistence.jpa;

import de.ollie.carp.vtt.core.service.model.BattleMap;
import de.ollie.carp.vtt.core.service.port.persistence.BattleMapPersistencePort;
import de.ollie.carp.vtt.persistence.jpa.mapper.BattleMapDboMapper;
import de.ollie.carp.vtt.persistence.jpa.repository.BattleMapDboRepository;
import jakarta.inject.Named;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;

@Named
@RequiredArgsConstructor
class BattleMapJpaPersistenceAdapter implements BattleMapPersistencePort {

	private final BattleMapDboMapper mapper;
	private final BattleMapDboRepository repository;

	@Override
	public BattleMap create(String name, byte[] image) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteById(UUID id) {
		// TODO Auto-generated method stub

	}

	@Override
	public Optional<BattleMap> findById(UUID id) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public List<BattleMap> list() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BattleMap update(BattleMap toSave) {
		// TODO Auto-generated method stub
		return null;
	}
}
