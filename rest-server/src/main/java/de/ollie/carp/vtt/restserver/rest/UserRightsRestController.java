package de.ollie.carp.vtt.restserver.rest;

import de.ollie.carp.vtt.restserver.core.service.UserService;
import de.ollie.carp.vtt.restserver.rest.api.UserRightsApi;
import io.jsonwebtoken.lang.Arrays;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserRightsRestController implements UserRightsApi {

	private final UserService userService;

	@Override
	public ResponseEntity<List<String>> getUserRights(UUID id) {
		return ResponseEntity.ok(
			userService.findById(id).map(u -> Arrays.asList(u.getRights().split(","))).orElse(List.of())
		);
	}
}
