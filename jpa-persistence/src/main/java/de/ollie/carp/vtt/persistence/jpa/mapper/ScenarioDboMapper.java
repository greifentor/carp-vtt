package de.ollie.carp.vtt.persistence.jpa.mapper;

import de.ollie.carp.vtt.core.service.model.Scenario;
import de.ollie.carp.vtt.persistence.jpa.dbo.ScenarioDbo;
import java.util.List;
import org.mapstruct.Mapper;

/**
 * GENERATED CODE - DO NOT TOUCH
 *
 * Remove this comment to suspend class from generation process.
 */
@Mapper(componentModel = "spring")
public interface ScenarioDboMapper {
	Scenario toModel(ScenarioDbo dbo);

	List<Scenario> toModels(List<ScenarioDbo> dbo);

	ScenarioDbo toDbo(Scenario model);

	List<ScenarioDbo> toDbos(List<Scenario> models);
}
