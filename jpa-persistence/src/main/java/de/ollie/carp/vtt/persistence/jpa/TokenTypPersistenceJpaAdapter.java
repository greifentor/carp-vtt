package de.ollie.carp.vtt.persistence.jpa;

import de.ollie.carp.vtt.core.service.model.TokenTyp;
import de.ollie.carp.vtt.core.service.port.persistence.TokenTypPersistencePort;
import de.ollie.carp.vtt.persistence.jpa.mapper.TokenTypDboMapper;
import de.ollie.carp.vtt.persistence.jpa.repository.TokenTypDboRepository;
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
public class TokenTypPersistenceJpaAdapter implements TokenTypPersistencePort {

	private final TokenTypDboMapper mapper;
	private final TokenTypDboRepository repository;

	@Override
	public TokenTyp create(String name, byte[] token) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteById(UUID id) {
		// TODO Auto-generated method stub
	}

	@Override
	public Optional<TokenTyp> findById(UUID id) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public Optional<TokenTyp> findByIdOrNameParticle(String name) {
		return Optional.empty();
	}

	@Override
	public List<TokenTyp> list() {
		return mapper.toModels(repository.findAll());
	}

	@Override
	public TokenTyp update(TokenTyp toSave) {
		return mapper.toModel(repository.save(mapper.toDbo(toSave)));
	}
}
