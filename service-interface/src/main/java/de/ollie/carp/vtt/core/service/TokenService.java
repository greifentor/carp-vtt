package de.ollie.carp.vtt.core.service;

import de.ollie.carp.vtt.core.service.model.Token;
import de.ollie.carp.vtt.core.service.model.TokenSize;
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
public interface TokenService {
	Token createToken(byte[] image, String name, TokenSize tokenSize);

	void deleteToken(UUID id);

	Optional<Token> findById(UUID id);

	List<Token> listTokens();

	Token updateToken(Token toSave);

	void updateAndPushToken(Token toSave);

	void pushToken(Token toPush);

	void pushDeleteToken(UUID idToDelete);

	void deleteAndPushDeleteToken(UUID idToDelete);
}
