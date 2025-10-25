package de.ollie.service.impl;

import static de.ollie.baselib.util.Check.ensure;

import de.ollie.vtt.core.service.TokenService;
import de.ollie.vtt.core.service.model.Token;
import de.ollie.vtt.core.service.port.persistence.TokenPersistencePort;
import jakarta.inject.Named;
import lombok.RequiredArgsConstructor;

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
