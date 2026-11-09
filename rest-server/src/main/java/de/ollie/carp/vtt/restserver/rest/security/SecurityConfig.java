package de.ollie.carp.vtt.restserver.rest.security;

import de.ollie.carp.vtt.restserver.core.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Configuration
	@EnableWebSecurity
	@RequiredArgsConstructor
	public class SecurityConfiguration {

		private final BearerTokenManager bearerChecker;
		private final UserService userService;

		@Bean
		SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
			http
				.csrf(AbstractHttpConfigurer::disable)
				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.authorizeHttpRequests(auth -> auth.anyRequest().authenticated())
				.addFilterBefore(
					new BearerTokenAuthenticationFilter(bearerChecker, userService),
					UsernamePasswordAuthenticationFilter.class
				)
				.exceptionHandling(ex ->
					ex.authenticationEntryPoint((request, response, authException) ->
						response.sendError(HttpServletResponse.SC_UNAUTHORIZED)
					)
				);
			return http.build();
		}
	}
}
