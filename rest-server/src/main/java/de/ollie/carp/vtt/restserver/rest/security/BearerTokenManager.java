package de.ollie.carp.vtt.restserver.rest.security;

import static de.ollie.baselib.util.Check.ensure;

import de.ollie.carp.vtt.restserver.core.service.UserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.inject.Named;
import jakarta.servlet.http.HttpServletRequest;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;

@Named
@RequiredArgsConstructor
class BearerTokenManager {

	static final String BEARER_PREFIX = "Bearer ";

	private final UserService userService;

	boolean hasBearer(HttpServletRequest request) {
		ensure(request != null, "request cannot be null!");
		String authorization = request.getHeader(HttpHeaders.AUTHORIZATION);
		return (authorization != null) && authorization.startsWith(BEARER_PREFIX);
	}

	String getToken(HttpServletRequest request) {
		ensure(request != null, "request cannot be null!");
		String authorization = request.getHeader(HttpHeaders.AUTHORIZATION);
		return !hasBearer(request) ? null : authorization.substring(7);
	}

	UUID getUserId(String token) {
		ensure(token != null, "token cannot be null!");
		Claims unverifiedClaims = Jwts
			.parser()
			.keyLocator(new DynamicKeyLocator(userService))
			.build()
			.parseSignedClaims(token)
			.getPayload();
		String uid = unverifiedClaims.get("sub", String.class);
		return uid != null ? UUID.fromString(uid) : null;
	}
}
