package de.ollie.carp.vtt.restclient.port;

import java.util.UUID;

public interface JwtService {
	String generateToken(UUID userId, long offsetMinutes, String userSecret);
}
