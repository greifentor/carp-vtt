package de.ollie.carp.vtt.migration;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
public class MigratorClientConfiguration {

	@Value("${migration.carp-maps-ws.port:8080}")
	private int port;
}
