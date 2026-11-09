package de.ollie.carp.vtt.restserver.rest;

import de.ollie.carp.vtt.restserver.core.service.model.Scenario;
import de.ollie.carp.vtt.restserver.rest.api.ScenarioApi;
import de.ollie.carp.vtt.restserver.rest.mapper.ScenarioDtoMapper;
import de.ollie.carp.vtt.restserver.rest.model.ScenarioDto;
import java.util.NoSuchElementException;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequiredArgsConstructor
public class ScenarioRestController implements ScenarioApi {

	private final ScenarioDtoMapper dtoMapper;
	private final de.ollie.carp.vtt.restserver.core.service.ScenarioService service;

	@Override
	public ResponseEntity<ScenarioDto> updateScenario(ScenarioDto dto) {
		checkRights(dto);
		Scenario model = dtoMapper.toModel(dto);
		model = service.updateScenario(model);
		return ResponseEntity.ofNullable(dtoMapper.toDto(model));
	}

	private void checkRights(ScenarioDto dto) {
		if ((!hasManagePrivateRight(dto.getOwner()) && !hasManagePublicRight(dto.getOwner()))) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Forbidden");
		}
	}

	private boolean hasManagePrivateRight(UUID owner) {
		return (
			SecurityContextHolder
				.getContext()
				.getAuthentication()
				.getAuthorities()
				.stream()
				.map(GrantedAuthority::getAuthority)
				.anyMatch(s -> s.startsWith("MANAGE_PRIVATE")) &&
			(owner != null)
		);
	}

	private boolean hasManagePublicRight(UUID owner) {
		return (
			SecurityContextHolder
				.getContext()
				.getAuthentication()
				.getAuthorities()
				.stream()
				.map(GrantedAuthority::getAuthority)
				.anyMatch(s -> s.startsWith("MANAGE_PUBLIC")) &&
			(owner == null)
		);
	}

	@Override
	public ResponseEntity<Void> removeScenario(UUID idtoDelete) {
		Scenario scenarioToDelete = service
			.findById(idtoDelete)
			.orElseThrow(() -> new NoSuchElementException("No scenario found with id: " + idtoDelete));
		checkRights(scenarioToDelete.getOwner());
		service.deleteScenario(idtoDelete);
		return ResponseEntity.accepted().build();
	}

	private void checkRights(UUID owner) {
		if ((!hasManagePrivateRight(owner) && !hasManagePublicRight(owner))) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Forbidden");
		}
	}
}
