package de.ollie.carp.vtt.web.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

	@Value("${viewer.name}")
	private String viewerName;

	@Value("${viewer.password}")
	private String viewerPassword;

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		return http
			.csrf(csrf -> csrf.disable())
			.authorizeHttpRequests(auth -> auth.requestMatchers("/**").authenticated().anyRequest().denyAll())
			.httpBasic(Customizer.withDefaults())
			.build();
	}

	@Bean
	InMemoryUserDetailsManager userDetailsService() {
		UserDetails user = User.withUsername(viewerName).password("{noop}" + viewerPassword).roles("VIEWER").build();
		return new InMemoryUserDetailsManager(user);
	}
}
