package de.ollie.carp.vtt.restclient.config;

import de.ollie.carp.vtt.restclient.ApiClient;
import de.ollie.carp.vtt.restclient.api.BattleMapApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Spring configuration for the generated CARP VTT REST client.
 *
 * <p>The base URL of the remote REST server can be configured via the property
 * {@code carp.vtt.rest-client.base-url} (defaults to {@code http://localhost:8080}).
 */
@Configuration
public class RestClientConfiguration {

	@Bean
	ApiClient carpVttApiClient(@Value("${carp.vtt.rest-client.base-url:http://localhost:8080}") String baseUrl) {
		ApiClient apiClient = new ApiClient();
		apiClient.setBasePath(baseUrl);
		return apiClient;
	}

	@Bean
	BattleMapApi battleMapApi(ApiClient carpVttApiClient) {
		return new BattleMapApi(carpVttApiClient);
	}
}
