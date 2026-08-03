package de.ollie.carp.vtt.restserver.rest;

import de.ollie.carp.vtt.restserver.core.service.TokenPositionService;
import de.ollie.carp.vtt.restserver.core.service.configuration.AccessRightConfiguration;
import de.ollie.carp.vtt.restserver.core.service.model.AccessRight;
import de.ollie.carp.vtt.restserver.core.service.model.TokenPosition;
import de.ollie.carp.vtt.restserver.rest.api.TokenPositionApi;
import de.ollie.carp.vtt.restserver.rest.mapper.TokenPositionDtoMapper;
import de.ollie.carp.vtt.restserver.rest.model.TokenPositionDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequiredArgsConstructor
public class TokenPositionRestController implements TokenPositionApi {

	private final AccessRightConfiguration accessRightConfiguration;
	private final TokenPositionDtoMapper tokenPositionDtoMapper;
	private final TokenPositionService tokenPositionService;

	@Override
	public ResponseEntity<TokenPositionDto> updateTokenPosition(@Valid TokenPositionDto tokenPositionDto) {
		if (!accessRightConfiguration.hasAccess(UserContextProvider.getUserId().userId(), AccessRight.UPDATE_POSITION)) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Forbidden");
		}
		TokenPosition tokenPosition = tokenPositionDtoMapper.toModel(tokenPositionDto);
		tokenPosition = tokenPositionService.updateTokenPosition(tokenPosition);
		return ResponseEntity.ofNullable(tokenPositionDtoMapper.toDto(tokenPosition));
	}
}
