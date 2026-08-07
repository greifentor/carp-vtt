package de.ollie.carp.vtt.restserver.rest;

import de.ollie.carp.vtt.restserver.core.service.BattleMapService;
import de.ollie.carp.vtt.restserver.core.service.configuration.AccessRightConfiguration;
import de.ollie.carp.vtt.restserver.core.service.model.AccessRight;
import de.ollie.carp.vtt.restserver.core.service.model.BattleMap;
import de.ollie.carp.vtt.restserver.rest.api.BattleMapApi;
import de.ollie.carp.vtt.restserver.rest.mapper.BattleMapDtoMapper;
import de.ollie.carp.vtt.restserver.rest.model.BattleMapDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequiredArgsConstructor
public class BattleMapRestController implements BattleMapApi {

	private final AccessRightConfiguration accessRightConfiguration;
	private final BattleMapDtoMapper battleMapDtoMapper;
	private final BattleMapService battleMapService;

	@Override
	public ResponseEntity<BattleMapDto> updateBattleMap(@Valid BattleMapDto battleMapDto) {
		if (!accessRightConfiguration.hasAccess(UserContextProvider.getUserId().userId(), AccessRight.UPDATE_OWN)) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Forbidden");
		}
		BattleMap battleMap = battleMapDtoMapper.toModel(battleMapDto);
		battleMap = battleMapService.updateBattleMap(battleMap);
		return ResponseEntity.ofNullable(battleMapDtoMapper.toDto(battleMap));
	}
}
