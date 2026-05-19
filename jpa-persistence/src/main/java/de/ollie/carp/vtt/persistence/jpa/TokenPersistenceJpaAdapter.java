package de.ollie.carp.vtt.persistence.jpa;

import de.ollie.carp.vtt.core.service.model.Token;
import de.ollie.carp.vtt.core.service.model.TokenSize;
import de.ollie.carp.vtt.core.service.port.persistence.TokenPersistencePort;
import de.ollie.carp.vtt.persistence.jpa.mapper.TokenDboMapper;
import de.ollie.carp.vtt.persistence.jpa.repository.TokenDboRepository;
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
public class TokenPersistenceJpaAdapter implements TokenPersistencePort {

	private final TokenDboMapper mapper;
	private final TokenDboRepository repository;

	@Override
	public Token create(String name, byte[] image, TokenSize tokenSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteById(UUID id) {
		// TODO Auto-generated method stub
	}

	@Override
	public Optional<Token> findById(UUID id) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public List<Token> list() {
		return mapper.toModels(repository.findAll());
	}

	@Override
	public Token update(Token toSave) {
		System.out.println(toSave);
		return mapper.toModel(repository.save(mapper.toDbo(toSave)));
	}
}
