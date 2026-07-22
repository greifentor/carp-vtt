package de.ollie.carp.vtt.restserver.rest;

import de.ollie.carp.vtt.restserver.core.service.BattleMapService;
import de.ollie.carp.vtt.restserver.core.service.model.BattleMap;
import de.ollie.carp.vtt.restserver.rest.api.BattleMapApi;
import de.ollie.carp.vtt.restserver.rest.mapper.BattleMapDtoMapper;
import de.ollie.carp.vtt.restserver.rest.model.BattleMapDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class BattleMapRestController implements BattleMapApi {

	private final BattleMapDtoMapper battleMapDtoMapper;
	private final BattleMapService battleMapService;

	@Override
	public ResponseEntity<BattleMapDto> updateBattleMap(@Valid BattleMapDto battleMapDto) {
		BattleMap battleMap = battleMapDtoMapper.toModel(battleMapDto);
		battleMap = battleMapService.updateBattleMap(battleMap);
		return ResponseEntity.ofNullable(battleMapDtoMapper.toDto(battleMap));
	}
}
