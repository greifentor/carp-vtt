package de.ollie.carp.vtt.core.service;

import de.ollie.carp.vtt.core.service.model.BattleMapPartyScenario;
import de.ollie.carp.vtt.core.service.model.Token;
import de.ollie.carp.vtt.core.service.model.TokenMapData;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import lombok.Generated;

/**
 * GENERATED CODE - DO NOT TOUCH
 *
 * Remove this comment to suspend class from generation process.
 */
@Generated
public interface TokenMapDataService {
	TokenMapData createTokenMapData(
		BattleMapPartyScenario battleMapPartyScenario,
		Token token,
		int counter,
		BigDecimal fieldX,
		BigDecimal fieldY
	);

	void deleteTokenMapData(UUID id);

	Optional<TokenMapData> findById(UUID id);

	List<TokenMapData> listTokenMapDatas();

	TokenMapData updateTokenMapData(TokenMapData toSave);

	Optional<TokenMapData> findByTokenAndBattleMapPartyScenario(UUID token, UUID battleMapPartyScenario);

	List<TokenMapData> findAllByBattleMapPartyScenario(BattleMapPartyScenario battleMapPartyScenario);
}
