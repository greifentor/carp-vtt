package de.ollie.carp.vtt.persistence.jpa.mapper;

import de.ollie.carp.vtt.core.service.model.DndTokenData;
import de.ollie.carp.vtt.persistence.jpa.dbo.DndTokenDataDbo;
import java.util.List;
import org.mapstruct.Mapper;

/**
 * GENERATED CODE - DO NOT TOUCH
 *
 * Remove this comment to suspend class from generation process.
 */
@Mapper(componentModel = "spring")
public interface DndTokenDataDboMapper {
	DndTokenData toModel(DndTokenDataDbo dbo);

	List<DndTokenData> toModels(List<DndTokenDataDbo> dbo);

	DndTokenDataDbo toDbo(DndTokenData model);

	List<DndTokenDataDbo> toDbos(List<DndTokenData> models);
}
