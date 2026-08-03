package de.ollie.carp.vtt.restserver.rest;

import de.ollie.carp.vtt.restserver.rest.exception.AuthenticationException;
import java.util.UUID;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

public class UserContextProvider {

	public record UserId(UUID userId) {}

	public static UserId getUserId() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth instanceof JwtAuthenticationToken jwtAuth) {
			Jwt jwt = jwtAuth.getToken();
			return new UserId(UUID.fromString(jwt.getSubject()));
		}
		throw new AuthenticationException();
	}
}
