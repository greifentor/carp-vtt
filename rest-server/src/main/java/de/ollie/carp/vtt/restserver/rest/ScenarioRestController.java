package de.ollie.carp.vtt.restserver.rest;

import de.ollie.carp.vtt.restserver.core.service.configuration.AccessRightConfiguration;
import de.ollie.carp.vtt.restserver.core.service.model.AccessRight;
import de.ollie.carp.vtt.restserver.core.service.model.Scenario;
import de.ollie.carp.vtt.restserver.rest.api.ScenarioApi;
import de.ollie.carp.vtt.restserver.rest.mapper.ScenarioDtoMapper;
import de.ollie.carp.vtt.restserver.rest.model.ScenarioDto;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequiredArgsConstructor
public class ScenarioRestController implements ScenarioApi {

	private final AccessRightConfiguration accessRightConfiguration;
	private final ScenarioDtoMapper dtoMapper;
	private final de.ollie.carp.vtt.restserver.core.service.ScenarioService service;

	@Override
	public ResponseEntity<ScenarioDto> updateScenario(ScenarioDto dto) {
		if (!accessRightConfiguration.hasAccess(UserContextProvider.getUserId().userId(), AccessRight.UPDATE_OWN)) {
			System.out.println("Access denied: Forbidden");
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Forbidden");
		}
		Scenario model = dtoMapper.toModel(dto);
		model = service.updateScenario(model);
		return ResponseEntity.ofNullable(dtoMapper.toDto(model));
	}

	@Override
	public ResponseEntity<Void> removeScenario(UUID idtoDelete) {
		if (!accessRightConfiguration.hasAccess(UserContextProvider.getUserId().userId(), AccessRight.UPDATE_OWN)) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Forbidden");
		}
		service.deleteScenario(idtoDelete);
		return ResponseEntity.accepted().build();
	}
}
