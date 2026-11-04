package de.ollie.carp.vtt.swing;

import jakarta.annotation.PostConstruct;
import jakarta.inject.Named;
import java.util.UUID;
import lombok.RequiredArgsConstructor;

@Named
@RequiredArgsConstructor
public class UserContext {

	private final UserConfiguration userConfiguration;

	private static UUID userId;

	@PostConstruct
	void postConstruct() {
		userId = userConfiguration.getUserId();
	}

	public static UUID getUserId() {
		return userId;
	}
}
