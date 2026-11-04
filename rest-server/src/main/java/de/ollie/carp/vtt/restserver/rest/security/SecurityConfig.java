package de.ollie.carp.vtt.restserver.rest.security;

import de.ollie.carp.vtt.restserver.core.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
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
	public class SecurityConfiguration {

		private final UserService userService;

		public SecurityConfiguration(UserService userService) {
			this.userService = userService;
		}

		@Bean
		SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
			http
				.csrf(AbstractHttpConfigurer::disable)
				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.authorizeHttpRequests(auth -> auth.anyRequest().authenticated())
				.addFilterBefore(new BearerTokenAuthenticationFilter(userService), UsernamePasswordAuthenticationFilter.class)
				.exceptionHandling(ex ->
					ex.authenticationEntryPoint((request, response, authException) ->
						response.sendError(HttpServletResponse.SC_UNAUTHORIZED)
					)
				);
			return http.build();
		}
	}
}
