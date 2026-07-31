package de.ollie.carp.vtt.restserver.rest.security;

import javax.crypto.spec.SecretKeySpec;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Value("${rest.server.communication.secret}")
	private String secret;

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		return http
			.csrf(csrf -> csrf.disable())
			.authorizeHttpRequests(auth ->
				auth
					// .requestMatchers("/actuator/**").permitAll()
					.anyRequest()
					.authenticated()
			)
			.oauth2ResourceServer(oauth -> oauth.jwt())
			.build();
	}

	@Bean
	public JwtDecoder jwtDecoder() {
		SecretKeySpec key = new SecretKeySpec(secret.getBytes(), "HmacSHA512");
		return NimbusJwtDecoder
			.withSecretKey(key)
			.macAlgorithm(org.springframework.security.oauth2.jose.jws.MacAlgorithm.HS512)
			.build();
	}
}
