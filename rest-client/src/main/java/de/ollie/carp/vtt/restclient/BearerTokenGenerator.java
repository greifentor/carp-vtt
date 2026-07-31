package de.ollie.carp.vtt.restclient;

import de.ollie.carp.vtt.restclient.config.RestClientConfiguration;
import de.ollie.carp.vtt.restclient.port.JwtService;
import jakarta.inject.Named;
import lombok.RequiredArgsConstructor;

@Named
@RequiredArgsConstructor
public class BearerTokenGenerator {

	private final JwtService jwtService;
	private final RestClientConfiguration configuration;

	public String create() {
		return jwtService.generateToken(
			configuration.getUserName(),
			configuration.getCommunicationValidityOffset(),
			configuration.getCommunicationSecret()
		);
	}
}
