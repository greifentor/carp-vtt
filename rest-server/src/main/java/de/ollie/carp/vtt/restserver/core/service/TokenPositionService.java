package de.ollie.carp.vtt.restserver.core.service;

import de.ollie.carp.vtt.restserver.core.service.model.TokenPosition;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import lombok.Generated;

/**
 * GENERATED CODE - DO NOT TOUCH
 *
 * Remove this comment to suspend class from generation process.
 */
@Generated
public interface TokenPositionService {
	TokenPosition createTokenPosition(
		UUID battleMapId,
		int coordinatesX,
		int coordinatesY,
		UUID partyId,
		UUID scenarioId,
		UUID tokenId
	);

	void deleteTokenPosition(UUID id);

	Optional<TokenPosition> findById(UUID id);

	List<TokenPosition> listTokenPositions();

	TokenPosition updateTokenPosition(TokenPosition toSave);
}
