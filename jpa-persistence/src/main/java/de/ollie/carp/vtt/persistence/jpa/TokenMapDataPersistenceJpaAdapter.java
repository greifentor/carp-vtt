package de.ollie.carp.vtt.persistence.jpa;

import de.ollie.carp.vtt.core.service.model.BattleMapPartyScenario;
import de.ollie.carp.vtt.core.service.model.BattleMapPartyScenario;
import de.ollie.carp.vtt.core.service.model.BattleMapPartyScenario;
import de.ollie.carp.vtt.core.service.model.BattleMapPartyScenario;
import de.ollie.carp.vtt.core.service.model.BattleMapPartyScenario;
import de.ollie.carp.vtt.core.service.model.BattleMapPartyScenario;
import de.ollie.carp.vtt.core.service.model.BattleMapPartyScenario;
import de.ollie.carp.vtt.core.service.model.BattleMapPartyScenario;
import de.ollie.carp.vtt.core.service.model.Token;
import de.ollie.carp.vtt.core.service.model.TokenMapData;
import de.ollie.carp.vtt.core.service.port.persistence.TokenMapDataPersistencePort;
import de.ollie.carp.vtt.persistence.jpa.mapper.BattleMapPartyScenarioDboMapper;
import de.ollie.carp.vtt.persistence.jpa.mapper.TokenMapDataDboMapper;
import de.ollie.carp.vtt.persistence.jpa.repository.TokenMapDataDboRepository;
import jakarta.inject.Named;
import java.math.BigDecimal;
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
public class TokenMapDataPersistenceJpaAdapter implements TokenMapDataPersistencePort {

	private final TokenMapDataDboMapper mapper;
	private final TokenMapDataDboRepository repository;

	private final BattleMapPartyScenarioDboMapper battleMapPartyScenarioDboMapper;

	@Override
	public TokenMapData create(
		BattleMapPartyScenario battleMapPartyScenario,
		Token token,
		int counter,
		BigDecimal fieldX,
		BigDecimal fieldY,
		boolean selected
	) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteById(UUID id) {
		repository.deleteById(id);
	}

	@Override
	public Optional<TokenMapData> findById(UUID id) {
		return repository.findById(id).map(mapper::toModel);
	}

	@Override
	public List<TokenMapData> findAllByBattleMapPartyScenario(BattleMapPartyScenario battleMapPartyScenario) {
		return mapper.toModels(
			repository.findAllByBattleMapPartyScenario(battleMapPartyScenarioDboMapper.toDbo(battleMapPartyScenario))
		);
	}

	@Override
	public List<TokenMapData> list() {
		return mapper.toModels(repository.findAll());
	}

	@Override
	public TokenMapData update(TokenMapData toSave) {
		return mapper.toModel(repository.save(mapper.toDbo(toSave)));
	}
}
