package de.ollie.carp.vtt.restserver.persistence.jpa.mapper;

import de.ollie.carp.vtt.restserver.core.service.model.Scenario;
import de.ollie.carp.vtt.restserver.persistence.jpa.dbo.ScenarioDbo;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.ObjectFactory;

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
