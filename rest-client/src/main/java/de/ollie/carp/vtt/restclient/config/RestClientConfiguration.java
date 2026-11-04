package de.ollie.carp.vtt.restclient.config;

import java.util.UUID;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
public class RestClientConfiguration {

	@Value("${rest.client.base.url}")
	private String baseUrl;

	@Value("${rest.communication.validity.offset:3}")
	private int communicationValidityOffset;

	@Value("${user.id}")
	private UUID userId;

	@Value("${user.secret}")
	private String userSecret;
}
