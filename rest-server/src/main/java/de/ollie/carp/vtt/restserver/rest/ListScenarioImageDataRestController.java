package de.ollie.carp.vtt.restserver.rest;

import de.ollie.carp.vtt.restserver.core.service.BattleMapService;
import de.ollie.carp.vtt.restserver.core.service.PartyService;
import de.ollie.carp.vtt.restserver.core.service.ScenarioService;
import de.ollie.carp.vtt.restserver.core.service.TokenPositionService;
import de.ollie.carp.vtt.restserver.core.service.model.BattleMap;
import de.ollie.carp.vtt.restserver.core.service.model.MapPartyScenario;
import de.ollie.carp.vtt.restserver.core.service.model.Party;
import de.ollie.carp.vtt.restserver.core.service.model.Scenario;
import de.ollie.carp.vtt.restserver.rest.api.ListScenarioImageDataApi;
import de.ollie.carp.vtt.restserver.rest.model.ImageInfoDto;
import java.util.List;
import java.util.NoSuchElementException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ListScenarioImageDataRestController implements ListScenarioImageDataApi {

	private final BattleMapService battleMapService;
	private final PartyService partyService;
	private final ScenarioService scenarioService;
	private final TokenPositionService tokenPositionService;

	@Override
	public ResponseEntity<List<ImageInfoDto>> findAllScenarioImageData() {
		return ResponseEntity.ok(tokenPositionService.findAllImageDataDistinct().stream().map(this::map).toList());
	}

	private ImageInfoDto map(MapPartyScenario mps) {
		BattleMap battleMap = battleMapService
			.findById(mps.battleMapId())
			.orElseThrow(() -> new NoSuchElementException("BattleMap not found with id: " + mps.battleMapId()));
		Party party = partyService
			.findById(mps.partyId())
			.orElseThrow(() -> new NoSuchElementException("Party not found with id: " + mps.partyId()));
		Scenario scenario = scenarioService
			.findById(mps.scenarioId())
			.orElseThrow(() -> new NoSuchElementException("Scenario not found with id: " + mps.scenarioId()));
		ImageInfoDto dto = new ImageInfoDto();
		dto.setName(battleMap.getName() + " / " + scenario.getName() + " / " + party.getName());
		dto.setUrl("/scenarios/" + scenario.getId() + "/parties/" + party.getId() + "/battlemaps/" + battleMap.getId());
		return dto;
	}
}
