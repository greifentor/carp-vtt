package de.ollie.carp.vtt.restserver.rest.security;

import de.ollie.carp.vtt.restserver.core.service.UserService;
import de.ollie.carp.vtt.restserver.core.service.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.lang.Arrays;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

@RequiredArgsConstructor
public class BearerTokenAuthenticationFilter extends OncePerRequestFilter {

	private final BearerTokenManager bearerTokenManager;
	private final UserService userService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
		throws ServletException, IOException {
		if (!bearerTokenManager.hasBearer(request)) {
			filterChain.doFilter(request, response);
			return;
		}
		String token = bearerTokenManager.getToken(request);
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
			User user = userService.findById(UUID.fromString(uid)).orElse(null);
			if (user != null) {
				System.out.println(user.getKey());
				Key key = Keys.hmacShaKeyFor(user.getKey().toString().getBytes(StandardCharsets.UTF_8));
				Jwts.parser().verifyWith((javax.crypto.SecretKey) key).build().parseSignedClaims(token);
				Authentication authentication = new UsernamePasswordAuthenticationToken(uid, null, getAuthorities(user));
				SecurityContextHolder.getContext().setAuthentication(authentication);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			SecurityContextHolder.clearContext();
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid token");
			return;
		}
		filterChain.doFilter(request, response);
	}

	private List<GrantedAuthority> getAuthorities(User user) {
		return Arrays
			.asList(user.getRights().split(","))
			.stream()
			.map(r -> (GrantedAuthority) new CarpVttGrantedAuthority(r))
			.toList();
	}
}
