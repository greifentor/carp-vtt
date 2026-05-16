package de.ollie.carp.vtt.core.service;

import de.ollie.carp.vtt.core.service.model.Token;
import java.util.List;

public interface TokenService {
	List<Token> findAll();

	Token save(Token toSave);
}
