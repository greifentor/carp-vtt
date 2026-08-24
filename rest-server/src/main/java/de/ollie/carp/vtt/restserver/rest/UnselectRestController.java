package de.ollie.carp.vtt.restserver.rest;

import de.ollie.carp.vtt.restserver.core.service.TokenDataService;
import de.ollie.carp.vtt.restserver.core.service.configuration.AccessRightConfiguration;
import de.ollie.carp.vtt.restserver.core.service.model.AccessRight;
import de.ollie.carp.vtt.restserver.rest.api.UnselectApi;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequiredArgsConstructor
public class UnselectRestController implements UnselectApi {

	private final AccessRightConfiguration accessRightConfiguration;
	private final TokenDataService tokenDataService;

	@Override
	public ResponseEntity<Void> unselect(UUID battleMapId, UUID partyId, UUID scenarioId) {
		if (!accessRightConfiguration.hasAccess(UserContextProvider.getUserId().userId(), AccessRight.UPDATE_OWN)) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Forbidden");
		}
		tokenDataService.unselect(battleMapId, partyId, scenarioId);
		return ResponseEntity.accepted().build();
	}
}
