package de.ollie.carp.vtt.core.service.impl;

import de.ollie.carp.vtt.core.service.model.TokenMapData;
import de.ollie.carp.vtt.core.service.port.persistence.TokenMapDataPersistencePort;
import jakarta.inject.Named;
import java.util.Optional;
import java.util.UUID;

@Named
class TokenMapDataServiceImpl extends GeneratedTokenMapDataServiceImpl {

	public TokenMapDataServiceImpl(TokenMapDataPersistencePort tokenMapDataPersistencePort) {
		super(tokenMapDataPersistencePort);
	}

	@Override
	public Optional<TokenMapData> findByTokenAndBattleMapPartyScenario(UUID token, UUID battleMapPartyScenario) {
		return null;
	}
}
