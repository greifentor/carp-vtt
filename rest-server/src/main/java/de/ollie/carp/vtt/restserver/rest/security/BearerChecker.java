package de.ollie.carp.vtt.restserver.rest.security;

import static de.ollie.baselib.util.Check.ensure;

import jakarta.inject.Named;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpHeaders;

@Named
class BearerChecker {

	static final String BEARER_PREFIX = "Bearer ";

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
}
