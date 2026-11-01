package de.ollie.carp.vtt.restclient.mapper;

import de.ollie.carp.vtt.core.service.model.Scenario;
import de.ollie.carp.vtt.restclient.model.ScenarioDto;
import jakarta.inject.Named;

@Named
public class ScenarioDtoMapper {

	public ScenarioDto map(Scenario model) {
		ScenarioDto dto = new ScenarioDto();
		dto.setId(model.getId());
		dto.setName(model.getName());
		return dto;
	}
}
