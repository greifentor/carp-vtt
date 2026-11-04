package de.ollie.carp.vtt.restserver.rest.security;

import de.ollie.carp.vtt.restserver.core.service.UserService;
import io.jsonwebtoken.LocatorAdapter;
import io.jsonwebtoken.ProtectedHeader;
import io.jsonwebtoken.security.Keys;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.UUID;

public class DynamicKeyLocator extends LocatorAdapter<Key> {

	private final UserService userService;

	public DynamicKeyLocator(UserService userService) {
		this.userService = userService;
	}

	@Override
	public Key locate(ProtectedHeader header) {
		System.out.println(header);
		String uid = (String) header.get("uid");
		System.out.println(uid);
		String secret = userService.loadSecretByUid(UUID.fromString(uid.trim()));
		return Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
	}
}
