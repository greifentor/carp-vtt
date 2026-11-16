package de.ollie.carp.vtt.swing;

import de.ollie.carp.vtt.core.service.port.web.UserRightsWebPort;
import jakarta.annotation.PostConstruct;
import jakarta.inject.Named;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;

@Named
@RequiredArgsConstructor
public class UserContext {

	private final UserConfiguration userConfiguration;
	private final UserRightsWebPort userRightsWebPort;

	private static UUID userId;
	private static List<String> userRights;

	@PostConstruct
	void postConstruct() {
		userId = userConfiguration.getUserId();
		userRights = userRightsWebPort.getUserRights(userId);
		System.out.println(userId + " > " + userRights);
	}

	public static UUID getUserId() {
		return userId;
	}

	public static boolean isManagePublicRightSet() {
		return (userRights != null) && userRights.contains("MANAGE_PUBLIC");
	}
}
