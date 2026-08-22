package de.ollie.carp.vtt.restclient.port;

public interface JwtService {
	String generateToken(String username, long offsetMinutes, String secretKey);
}
