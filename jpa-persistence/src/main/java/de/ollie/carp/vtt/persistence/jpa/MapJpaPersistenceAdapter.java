package de.ollie.carp.vtt.persistence.jpa;

import de.ollie.carp.vtt.core.service.model.Map;
import de.ollie.carp.vtt.core.service.port.persistence.MapPersistencePort;
import de.ollie.carp.vtt.persistence.jpa.mapper.MapDboMapper;
import de.ollie.carp.vtt.persistence.jpa.repository.MapDboRepository;
import jakarta.inject.Named;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;

@Named
@RequiredArgsConstructor
class MapJpaPersistenceAdapter implements MapPersistencePort {

	private final MapDboMapper mapper;
	private final MapDboRepository repository;

	@Override
	public Map create(String name, byte[] image) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteById(UUID id) {
		// TODO Auto-generated method stub

	}

	@Override
	public Optional<Map> findById(UUID id) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public List<Map> list() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map update(Map toSave) {
		return mapper.toModel(repository.save(mapper.toDbo(toSave)));
	}
}
