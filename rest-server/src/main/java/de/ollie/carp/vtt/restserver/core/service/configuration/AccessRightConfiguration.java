package de.ollie.carp.vtt.restserver.core.service.configuration;

import static de.ollie.baselib.util.Check.ensure;

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
		ensure(right != null, "right cannot be null!");
		ensure(userId != null, "user id cannot be null!");
		return rights.getOrDefault(userId, List.of()).contains(right);
	}

	public boolean hasAccessToAtLeastOneRight(UUID userId, AccessRight... rights) {
		boolean result = false;
		for (AccessRight right : rights) {
			result = result || hasAccess(userId, right);
		}
		return result;
	}
}
