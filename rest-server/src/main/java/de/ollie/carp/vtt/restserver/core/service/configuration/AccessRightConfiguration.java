package de.ollie.carp.vtt.restserver.core.service.configuration;

import de.ollie.carp.vtt.restserver.core.service.model.AccessRight;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "access")
public class AccessRightConfiguration {

	private Map<UUID, List<AccessRight>> rights = new HashMap<>();

	public Map<UUID, List<AccessRight>> getRights() {
		return rights;
	}

	public void setRights(Map<UUID, List<AccessRight>> rights) {
		this.rights = rights;
	}

	public boolean hasAccess(UUID userId, AccessRight right) {
		return rights.getOrDefault(userId, List.of()).contains(right);
	}
}
