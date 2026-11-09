package de.ollie.carp.vtt.restserver.rest.security;

import de.ollie.carp.vtt.restserver.core.service.UserService;
import io.jsonwebtoken.LocatorAdapter;
import io.jsonwebtoken.ProtectedHeader;
import io.jsonwebtoken.security.Keys;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.UUID;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DynamicKeyLocator extends LocatorAdapter<Key> {

	private final UserService userService;

	@Override
	public Key locate(ProtectedHeader header) {
		try {
			String uid = (String) header.get("uid");
			String secret = userService.loadSecretByUid(UUID.fromString(uid.trim()));
			return Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}
}
