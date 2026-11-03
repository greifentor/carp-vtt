package de.ollie.carp.vtt.restserver.persistence.jpa.mapper;

import de.ollie.carp.vtt.restserver.core.service.model.DndData;
import de.ollie.carp.vtt.restserver.persistence.jpa.dbo.DndDataDbo;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.ObjectFactory;

/**
 * GENERATED CODE - DO NOT TOUCH
 *
 * Remove this comment to suspend class from generation process.
 */
@Mapper(componentModel = "spring")
public interface DndDataDboMapper {
	DndData toModel(DndDataDbo dbo);

	List<DndData> toModels(List<DndDataDbo> dbo);

	DndDataDbo toDbo(DndData model);

	List<DndDataDbo> toDbos(List<DndData> models);
}
