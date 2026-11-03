package de.ollie.carp.vtt.restserver.rest.mapper;

import de.ollie.carp.vtt.restserver.core.service.model.Scenario;
import de.ollie.carp.vtt.restserver.rest.model.ScenarioDto;
import java.util.List;
import org.mapstruct.Mapper;

/**
 * GENERATED CODE - DO NOT TOUCH
 *
 * Remove this comment to suspend class from generation process.
 */
@Mapper(componentModel = "spring")
public interface ScenarioDtoMapper {
	Scenario toModel(ScenarioDto dto);

	List<Scenario> toModels(List<ScenarioDto> dto);

	ScenarioDto toDto(Scenario model);

	List<ScenarioDto> toDtos(List<Scenario> models);
}
