package de.ollie.carp.vtt.service.impl;

import static de.ollie.baselib.util.Check.ensure;

import de.ollie.carp.vtt.core.service.TokenService;
import de.ollie.carp.vtt.core.service.model.Token;
import de.ollie.carp.vtt.core.service.port.persistence.TokenPersistencePort;
import jakarta.inject.Named;
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

	private final TokenPersistencePort persistencePort;

	@Override
	public Token save(Token toSave) {
		ensure(toSave != null, "token cannot be null!");
		return persistencePort.update(toSave);
	}
}
