package de.ollie.carp.vtt.restserver.core.service.impl;

import de.ollie.carp.vtt.restserver.core.service.ScenarioService;
import de.ollie.carp.vtt.restserver.core.service.model.Scenario;
import de.ollie.carp.vtt.restserver.core.service.port.persistence.ScenarioPersistencePort;
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
class ScenarioServiceImpl implements ScenarioService {

	private final ScenarioPersistencePort scenarioPersistencePort;

	@Override
	public Scenario createScenario(String name, UUID owner) {
		return scenarioPersistencePort.create(name, owner);
	}

	@Override
	public void deleteScenario(UUID id) {
		scenarioPersistencePort.deleteById(id);
	}

	@Override
	public Optional<Scenario> findById(UUID id) {
		return scenarioPersistencePort.findById(id);
	}

	@Override
	public List<Scenario> listScenarios() {
		return scenarioPersistencePort.list();
	}

	@Override
	public Scenario updateScenario(Scenario toSave) {
		return scenarioPersistencePort.update(toSave);
	}
}
