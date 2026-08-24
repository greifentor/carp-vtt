package de.ollie.carp.vtt.restserver.persistence.jpa;

import de.ollie.carp.vtt.restserver.core.service.model.TokenPosition;
import de.ollie.carp.vtt.restserver.core.service.port.persistence.TokenPositionPersistencePort;
import de.ollie.carp.vtt.restserver.persistence.jpa.mapper.TokenPositionDboMapper;
import de.ollie.carp.vtt.restserver.persistence.jpa.repository.ExtendedTokenPositionRepository;
import de.ollie.carp.vtt.restserver.persistence.jpa.repository.TokenPositionDboRepository;
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
public class TokenPositionPersistenceJpaAdapter implements TokenPositionPersistencePort {

	private final ExtendedTokenPositionRepository extendedRepository;
	private final TokenPositionDboMapper mapper;
	private final TokenPositionDboRepository repository;

	@Override
	public TokenPosition create(
		UUID battleMapId,
		int coordinateX,
		int coordinateY,
		UUID partyId,
		UUID scenarioId,
		UUID tokenId,
		boolean selected
	) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteById(UUID id) {
		// TODO Auto-generated method stub
	}

	@Override
	public Optional<TokenPosition> findById(UUID id) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public List<TokenPosition> list() {
		return mapper.toModels(repository.findAll());
	}

	@Override
	public TokenPosition update(TokenPosition toSave) {
		if (toSave.isSelected()) {
			extendedRepository.resetSelectedForBattleMapPartyAndScenario(
				toSave.getBattleMapId(),
				toSave.getPartyId(),
				toSave.getScenarioId()
			);
		}
		return mapper.toModel(repository.save(mapper.toDbo(toSave)));
	}
}
