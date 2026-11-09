package de.ollie.carp.vtt.restserver.rest;

import de.ollie.carp.vtt.restserver.core.service.model.Scenario;
import de.ollie.carp.vtt.restserver.rest.api.ScenarioApi;
import de.ollie.carp.vtt.restserver.rest.mapper.ScenarioDtoMapper;
import de.ollie.carp.vtt.restserver.rest.model.ScenarioDto;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequiredArgsConstructor
public class ScenarioRestController implements ScenarioApi {

	static final String MANAGE_PRIVATE = "MANAGE_PRIVATE";
	static final String MANAGE_PUBLIC = "MANAGE_PUBLIC";

	private final ScenarioDtoMapper dtoMapper;
	private final de.ollie.carp.vtt.restserver.core.service.ScenarioService service;

	@Override
	public ResponseEntity<ScenarioDto> updateScenario(ScenarioDto dto) {
		checkRights(dto.getOwner());
		service.findById(dto.getId()).ifPresent(persisted -> checkRights(persisted.getOwner()));
		Scenario model = dtoMapper.toModel(dto);
		model = service.updateScenario(model);
		return ResponseEntity.ofNullable(dtoMapper.toDto(model));
	}

	@Override
	public ResponseEntity<Void> removeScenario(UUID idtoDelete) {
		Scenario scenarioToDelete = service
			.findById(idtoDelete)
			.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No scenario found with id: " + idtoDelete));
		checkRights(scenarioToDelete.getOwner());
		service.deleteScenario(idtoDelete);
		return ResponseEntity.accepted().build();
	}

	private void checkRights(UUID owner) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!hasManagePrivateRight(authentication, owner) && !hasManagePublicRight(authentication, owner)) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Forbidden");
		}
	}

	private boolean hasManagePrivateRight(Authentication authentication, UUID owner) {
		return (
			(owner != null) &&
			owner.toString().equalsIgnoreCase(authentication.getName()) &&
			hasAuthority(authentication, MANAGE_PRIVATE)
		);
	}

	private boolean hasManagePublicRight(Authentication authentication, UUID owner) {
		return (owner == null) && hasAuthority(authentication, MANAGE_PUBLIC);
	}

	private boolean hasAuthority(Authentication authentication, String authority) {
		return authentication
			.getAuthorities()
			.stream()
			.map(GrantedAuthority::getAuthority)
			.anyMatch(a -> a.startsWith(authority));
	}
}
