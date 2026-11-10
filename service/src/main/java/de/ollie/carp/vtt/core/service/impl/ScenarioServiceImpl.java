package de.ollie.carp.vtt.core.service.impl;

import de.ollie.carp.vtt.core.service.ScenarioService;
import de.ollie.carp.vtt.core.service.model.Scenario;
import de.ollie.carp.vtt.core.service.port.persistence.ScenarioPersistencePort;
import de.ollie.carp.vtt.core.service.port.web.ScenarioWebPort;
import jakarta.inject.Named;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import lombok.Generated;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

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
	private final ScenarioWebPort scenarioWebPort;

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

	@Override
	public Scenario pushScenario(Scenario toPush) {
		return scenarioWebPort.pushScenario(toPush);
	}

	@Override
	public void pushDeleteScenario(UUID idToDelete) {
		scenarioWebPort.deleteScenario(idToDelete);
	}

	@Override
	@Transactional
	public void updateAndPushScenario(Scenario toSave) {
		scenarioWebPort.pushScenario(toSave);
		scenarioPersistencePort.update(toSave);
	}

	@Override
	@Transactional
	public void deleteAndPushDeleteScenario(UUID idToDelete) {
		scenarioWebPort.deleteScenario(idToDelete);
		scenarioPersistencePort.deleteById(idToDelete);
	}
}
