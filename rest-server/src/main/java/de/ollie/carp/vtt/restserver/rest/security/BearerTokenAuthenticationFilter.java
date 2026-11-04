package de.ollie.carp.vtt.restserver.rest.security;

import de.ollie.carp.vtt.restserver.core.service.UserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Collections;
import java.util.UUID;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

public class BearerTokenAuthenticationFilter extends OncePerRequestFilter {

	private final UserService userService;

	public BearerTokenAuthenticationFilter(UserService userService) {
		this.userService = userService;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
		throws ServletException, IOException {
		String authorization = request.getHeader(HttpHeaders.AUTHORIZATION);
		if (authorization == null || !authorization.startsWith("Bearer ")) {
			filterChain.doFilter(request, response);
			return;
		}
		String token = authorization.substring(7);
		try {
			// JWT nur decodieren
			Claims unverifiedClaims = Jwts
				.parser()
				.keyLocator(new DynamicKeyLocator(userService))
				.build()
				.parseSignedClaims(token)
				.getPayload();
			String uid = unverifiedClaims.get("sub", String.class);
			if (uid == null) {
				throw new JwtException("uid missing");
			}
			String secret = userService.loadSecretByUid(UUID.fromString(uid));
			System.out.println(secret);
			Key key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
			// JWT validieren
			Jwts.parser().verifyWith((javax.crypto.SecretKey) key).build().parseSignedClaims(token);
			Authentication authentication = new UsernamePasswordAuthenticationToken(uid, null, Collections.emptyList());
			SecurityContextHolder.getContext().setAuthentication(authentication);
		} catch (Exception ex) {
			ex.printStackTrace();
			SecurityContextHolder.clearContext();
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid token");
			return;
		}
		filterChain.doFilter(request, response);
	}
}
