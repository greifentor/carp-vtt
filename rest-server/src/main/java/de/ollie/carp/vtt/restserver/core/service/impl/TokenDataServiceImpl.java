package de.ollie.carp.vtt.restserver.core.service.impl;

import de.ollie.carp.vtt.core.service.model.Coordinates;
import de.ollie.carp.vtt.restserver.core.service.TokenDataService;
import de.ollie.carp.vtt.restserver.core.service.TokenService;
import de.ollie.carp.vtt.restserver.core.service.model.Token;
import de.ollie.carp.vtt.restserver.core.service.model.TokenData;
import de.ollie.carp.vtt.restserver.core.service.model.TokenPosition;
import de.ollie.carp.vtt.restserver.core.service.port.persistence.ExtendedTokenPositionPersistencePort;
import jakarta.inject.Named;
import java.math.BigDecimal;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;
import lombok.RequiredArgsConstructor;

@Named
@RequiredArgsConstructor
public class TokenDataServiceImpl implements TokenDataService {

	private final ExtendedTokenPositionPersistencePort extendedTokenPositionPersistencePort;
	private final TokenService tokenService;

	@Override
	public List<TokenData> findAllBy(UUID battleMapId, UUID partyId, UUID scenarioId) {
		return extendedTokenPositionPersistencePort
			.findAllBy(battleMapId, partyId, scenarioId)
			.stream()
			.map(this::map)
			.toList();
	}

	private TokenData map(TokenPosition tp) {
		Token token = tokenService
			.findById(tp.getTokenId())
			.orElseThrow(() -> new NoSuchElementException("no token found with id: " + tp.getTokenId()));
		return new TokenData()
			.setCoordinates(
				new Coordinates().setFieldX(new BigDecimal(tp.getCoordinateX())).setFieldY(new BigDecimal(tp.getCoordinateY()))
			)
			.setId(tp.getId())
			.setImage(token.getImage())
			.setName(token.getName())
			.setSelected(tp.isSelected())
			.setTokenSize(de.ollie.carp.vtt.core.service.model.TokenSize.valueOf(token.getTokenSize().name()));
	}

	@Override
	public TokenData getSelectedToken(UUID battleMapId, UUID partyId, UUID scenarioId) {
		return findAllBy(battleMapId, partyId, scenarioId).stream().filter(t -> t.isSelected()).findFirst().orElse(null);
	}
}
