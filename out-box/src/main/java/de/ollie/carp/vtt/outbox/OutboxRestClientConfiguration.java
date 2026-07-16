package de.ollie.carp.vtt.outbox;

import de.ollie.carp.vtt.restclient.config.RestClientConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Makes the generated REST client (and thus the {@code BattleMapApi} bean) available to the out-box adapters.
 *
 * <p>The base URL of the remote server is configured via {@code carp.vtt.rest-client.base-url} (see
 * {@link RestClientConfiguration}).
 */
@Configuration
@Import(RestClientConfiguration.class)
public class OutboxRestClientConfiguration {}
