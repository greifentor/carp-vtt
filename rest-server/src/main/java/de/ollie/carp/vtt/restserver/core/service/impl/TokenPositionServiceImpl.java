package de.ollie.carp.vtt.restserver.core.service.impl;

import de.ollie.carp.vtt.restserver.core.service.TokenPositionService;
import de.ollie.carp.vtt.restserver.core.service.model.TokenPosition;
import de.ollie.carp.vtt.restserver.core.service.port.persistence.TokenPositionPersistencePort;
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
class TokenPositionServiceImpl implements TokenPositionService {

	private final TokenPositionPersistencePort tokenPositionPersistencePort;

	@Override
	public TokenPosition createTokenPosition(
		UUID battleMapId,
		int coordinateX,
		int coordinateY,
		UUID partyId,
		UUID scenarioId,
		UUID tokenId
	) {
		return tokenPositionPersistencePort.create(battleMapId, coordinateX, coordinateY, partyId, scenarioId, tokenId);
	}

	@Override
	public void deleteTokenPosition(UUID id) {
		tokenPositionPersistencePort.deleteById(id);
	}

	@Override
	public Optional<TokenPosition> findById(UUID id) {
		return tokenPositionPersistencePort.findById(id);
	}

	@Override
	public List<TokenPosition> listTokenPositions() {
		return tokenPositionPersistencePort.list();
	}

	@Override
	public TokenPosition updateTokenPosition(TokenPosition toSave) {
		return tokenPositionPersistencePort.update(toSave);
	}
}
