package de.ollie.carp.vtt.core.service.impl;

import de.ollie.carp.vtt.core.service.TokenMapDataService;
import de.ollie.carp.vtt.core.service.model.TokenMapData;
import de.ollie.carp.vtt.core.service.model.BattleMapPartyScenario;
import de.ollie.carp.vtt.core.service.model.Token;
import de.ollie.carp.vtt.core.service.port.persistence.TokenMapDataPersistencePort;
import java.util.List;
import java.util.Optional;
import lombok.Generated;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

/**
 * GENERATED CODE - DO NOT TOUCH
 *
 * Remove this comment to suspend class from generation process.
 */
@Generated
@RequiredArgsConstructor
abstract class GeneratedTokenMapDataServiceImpl implements TokenMapDataService {

protected final TokenMapDataPersistencePort tokenMapDataPersistencePort;

	@Override
	public TokenMapData createTokenMapData(BattleMapPartyScenario battleMapPartyScenario, Token token, int counter, BigDecimal fieldX, BigDecimal fieldY) {
		return tokenMapDataPersistencePort.create(battleMapPartyScenario, token, counter, fieldX, fieldY);
	}

	@Override
	public void deleteTokenMapData(UUID id) {
		tokenMapDataPersistencePort.deleteById(id);
	}

	@Override
	public Optional<TokenMapData> findById(UUID id) {
		return tokenMapDataPersistencePort.findById(id);
	}

	@Override
	public List<TokenMapData> findAllByBattleMapPartyScenario(BattleMapPartyScenario battleMapPartyScenario) {
		return tokenMapDataPersistencePort.findAllByBattleMapPartyScenario(battleMapPartyScenario);
	}

	@Override
	public List<TokenMapData> listTokenMapDatas() {
		return tokenMapDataPersistencePort.list();
	}
	
	@Override
	public TokenMapData updateTokenMapData(TokenMapData toSave) {
		return tokenMapDataPersistencePort.update(toSave);
	}

}