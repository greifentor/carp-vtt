package de.ollie.carp.vtt.core.service.port.persistence;

import de.ollie.carp.vtt.core.service.model.BattleMapPartyScenario;
import de.ollie.carp.vtt.core.service.model.Token;
import de.ollie.carp.vtt.core.service.model.TokenMapData;
import jakarta.inject.Named;
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
@Named
public interface TokenMapDataPersistencePort {
	TokenMapData create(
		BattleMapPartyScenario battleMapPartyScenario,
		Token token,
		int counter,
		BigDecimal fieldX,
		BigDecimal fieldY
	);

	void deleteById(UUID id);

	Optional<TokenMapData> findById(UUID id);

	List<TokenMapData> list();

	TokenMapData update(TokenMapData toSave);
}
