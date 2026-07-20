package de.ollie.carp.vtt.restserver.core.service.port.persistence;

import de.ollie.carp.vtt.restserver.core.service.model.TokenPosition;
import jakarta.inject.Named;
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
@Named
public interface TokenPositionPersistencePort {
	TokenPosition create(
		UUID battleMapId,
		int coordinatesX,
		int coordinatesY,
		UUID partyId,
		UUID scenarioId,
		UUID tokenId
	);

	void deleteById(UUID id);

	Optional<TokenPosition> findById(UUID id);

	List<TokenPosition> list();

	TokenPosition update(TokenPosition toSave);
}
