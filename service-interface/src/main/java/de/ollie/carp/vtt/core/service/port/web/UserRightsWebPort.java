package de.ollie.carp.vtt.core.service.port.web;

import java.util.List;
import java.util.UUID;

public interface UserRightsWebPort {
	List<String> getUserRights(UUID userId);
}
