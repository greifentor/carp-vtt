package de.ollie.carp.vtt.restclient.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
public class RestClientConfiguration {

	@Value("${rest.client.base.url}")
	private String baseUrl;
}
