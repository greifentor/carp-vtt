package de.ollie.carp.vtt.restserver.core.service;

import de.ollie.carp.vtt.restserver.core.service.model.Token;
import de.ollie.carp.vtt.restserver.core.service.model.TokenSize;
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
	Token createToken(String name, byte[] image, TokenSize tokenSize);

	void deleteToken(UUID id);

	Optional<Token> findById(UUID id);

	List<Token> listTokens();

	Token updateToken(Token toSave);
}
