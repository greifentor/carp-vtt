package de.ollie.carp.vtt.persistence.jpa;

import de.ollie.carp.vtt.core.service.model.Token;
import de.ollie.carp.vtt.core.service.port.persistence.TokenPersistencePort;
import de.ollie.carp.vtt.persistence.jpa.mapper.TokenDboMapper;
import de.ollie.carp.vtt.persistence.jpa.repository.TokenDboRepository;
import jakarta.inject.Named;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;

@Named
@RequiredArgsConstructor
class TokenJpaPersistenceAdapter implements TokenPersistencePort {

	private final TokenDboMapper mapper;
	private final TokenDboRepository repository;

	@Override
	public Token create(String name, byte[] image) {
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Token update(Token toSave) {
		return mapper.toModel(repository.save(mapper.toDbo(toSave)));
	}
}
