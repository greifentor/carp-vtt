package de.ollie.carp.vtt.restserver.rest;

import de.ollie.carp.vtt.restserver.core.service.TokenService;
import de.ollie.carp.vtt.restserver.core.service.model.Token;
import de.ollie.carp.vtt.restserver.rest.api.TokenApi;
import de.ollie.carp.vtt.restserver.rest.mapper.TokenDtoMapper;
import de.ollie.carp.vtt.restserver.rest.model.TokenDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TokenRestController implements TokenApi {

	private final TokenDtoMapper tokenDtoMapper;
	private final TokenService tokenService;

	@Override
	public ResponseEntity<TokenDto> updateToken(@Valid TokenDto tokenDto) {
		Token token = tokenDtoMapper.toModel(tokenDto);
		token = tokenService.updateToken(token);
		return ResponseEntity.ofNullable(tokenDtoMapper.toDto(token));
	}

	@Override
	public ResponseEntity<TokenDto> removeToken(@Valid TokenDto tokenDto) {
		Token token = tokenDtoMapper.toModel(tokenDto);
		tokenService.deleteToken(token.getId());
		return ResponseEntity.ofNullable(tokenDtoMapper.toDto(token));
	}
}
