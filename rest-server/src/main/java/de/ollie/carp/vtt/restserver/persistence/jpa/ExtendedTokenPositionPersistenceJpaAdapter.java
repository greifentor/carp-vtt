package de.ollie.carp.vtt.restserver.persistence.jpa;

import de.ollie.carp.vtt.restserver.core.service.model.TokenPosition;
import de.ollie.carp.vtt.restserver.core.service.port.persistence.ExtendedTokenPositionPersistencePort;
import de.ollie.carp.vtt.restserver.persistence.jpa.mapper.TokenPositionDboMapper;
import de.ollie.carp.vtt.restserver.persistence.jpa.repository.ExtendedTokenPositionRepository;
import jakarta.inject.Named;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;

@Named
@RequiredArgsConstructor
public class ExtendedTokenPositionPersistenceJpaAdapter implements ExtendedTokenPositionPersistencePort {

	private final TokenPositionDboMapper mapper;
	private final ExtendedTokenPositionRepository repository;

	@Override
	public List<TokenPosition> findAllBy(UUID battleMapId, UUID partyId, UUID scenarioId) {
		return mapper.toModels(repository.findAllByBattleMapIdAndPartyIdAndScenarioId(battleMapId, partyId, scenarioId));
	}

	@Override
	public TokenPosition getSelectedToken(UUID battleMapId, UUID partyId, UUID scenarioId) {
		return mapper.toModel(
			repository.findByBattleMapIdAndPartyIdAndScenarioIdAndSelectedIsTrue(battleMapId, partyId, scenarioId)
		);
	}
}
