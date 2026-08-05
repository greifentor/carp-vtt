package de.ollie.carp.vtt.restserver.rest;

import de.ollie.carp.vtt.restserver.core.service.TokenService;
import de.ollie.carp.vtt.restserver.core.service.configuration.AccessRightConfiguration;
import de.ollie.carp.vtt.restserver.core.service.model.AccessRight;
import de.ollie.carp.vtt.restserver.core.service.model.Token;
import de.ollie.carp.vtt.restserver.rest.api.TokenApi;
import de.ollie.carp.vtt.restserver.rest.mapper.TokenDtoMapper;
import de.ollie.carp.vtt.restserver.rest.model.TokenDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequiredArgsConstructor
public class TokenRestController implements TokenApi {

	private final AccessRightConfiguration accessRightConfiguration;
	private final TokenDtoMapper tokenDtoMapper;
	private final TokenService tokenService;

	@Override
	public ResponseEntity<TokenDto> updateToken(@Valid TokenDto tokenDto) {
		if (!accessRightConfiguration.hasAccess(UserContextProvider.getUserId().userId(), AccessRight.UPDATE_OWN)) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Forbidden");
		}
		Token token = tokenDtoMapper.toModel(tokenDto);
		token = tokenService.updateToken(token);
		return ResponseEntity.ofNullable(tokenDtoMapper.toDto(token));
	}
}
