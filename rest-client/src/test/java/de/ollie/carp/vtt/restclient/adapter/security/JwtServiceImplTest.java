package de.ollie.carp.vtt.restclient.adapter.security;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class JwtServiceImplTest {

	private final JwtServiceImpl jwtService = new JwtServiceImpl();

	@Test
	void tokenIsGenerated() {
		// Prepare
		String username = "oliver";
		long offset = 60;
		String key = "mein-super-geheimer-und-langer-key-der-64-byte-haben-muss";
		// Run
		String token = jwtService.generateToken(username, offset, key);
		// Check
		assertNotNull(token);
	}

	@Test
	void tokenContainsHs512Header() {
		// Prepare
		String token = jwtService.generateToken("oliver", 10, "mein-super-geheimer-und-langer-key-der-64-byte-haben-muss");
		// Run
		String headerJson = new String(java.util.Base64.getUrlDecoder().decode(token.split("\\.")[0]));
		// Check
		assertTrue(headerJson.contains("\"alg\":\"HS512\""));
	}

	@Test
	void tokenContainsCorrectSubject() {
		// Prepare
		String username = "oliver";
		String token = jwtService.generateToken(username, 10, "mein-super-geheimer-und-langer-key-der-64-byte-haben-muss");
		// Run
		String payloadJson = new String(java.util.Base64.getUrlDecoder().decode(token.split("\\.")[1]));
		// Check
		assertTrue(payloadJson.contains("\"sub\":\"" + username + "\""));
	}
}
