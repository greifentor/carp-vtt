package de.ollie.carp.vtt.core.service.impl;

import de.ollie.carp.vtt.core.service.TokenService;
import de.ollie.carp.vtt.core.service.model.Token;
import de.ollie.carp.vtt.core.service.model.TokenSize;
import de.ollie.carp.vtt.core.service.port.persistence.TokenPersistencePort;
import de.ollie.carp.vtt.core.service.port.web.TokenWebPort;
import jakarta.inject.Named;
import java.util.List;
import java.util.Optional;
import lombok.Generated;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

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
	private final TokenWebPort tokenWebPort;

	@Override
	public Token createToken(byte[] image, String name, UUID owner, TokenSize tokenSize) {
		return tokenPersistencePort.create(image, name, owner, tokenSize);
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

	@Override
	public void pushToken(Token toPush) {
		tokenWebPort.pushToken(toPush);
	}

	@Override
	public void pushDeleteToken(UUID idToDelete) {
		tokenWebPort.deleteToken(idToDelete);
	}

	@Override
	@Transactional
	public void updateAndPushToken(Token toSave) {
		tokenWebPort.pushToken(toSave);
		tokenPersistencePort.update(toSave);
	}

	@Override
	@Transactional
	public void deleteAndPushDeleteToken(UUID idToDelete) {
		tokenWebPort.deleteToken(idToDelete);
		tokenPersistencePort.deleteById(idToDelete);
	}

}