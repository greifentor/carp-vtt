package de.ollie.carp.vtt.restserver.core.service.impl;

import de.ollie.carp.vtt.restserver.core.service.TokenService;
import de.ollie.carp.vtt.restserver.core.service.model.Token;
import de.ollie.carp.vtt.restserver.core.service.model.TokenSize;
import de.ollie.carp.vtt.restserver.core.service.port.persistence.TokenPersistencePort;
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
class TokenServiceImpl implements TokenService {

	private final TokenPersistencePort tokenPersistencePort;

	@Override
	public Token createToken(String name, byte[] image, TokenSize tokenSize) {
		return tokenPersistencePort.create(name, image, tokenSize);
	}

	@Override
	public void deleteToken(UUID id) {
		tokenPersistencePort.deleteById(id);
	}

	@Override
	public Optional<Token> findById(UUID id) {
		return tokenPersistencePort.findById(id);
	}

	@Override
	public List<Token> listTokens() {
		return tokenPersistencePort.list();
	}

	@Override
	public Token updateToken(Token toSave) {
		return tokenPersistencePort.update(toSave);
	}
}
