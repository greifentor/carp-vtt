package de.ollie.carp.vtt.persistence.jpa.mapper;

import de.ollie.carp.vtt.core.service.model.DndTokenMapData;
import de.ollie.carp.vtt.core.service.model.DndTokenMapDataImpl;
import de.ollie.carp.vtt.persistence.jpa.dbo.DndTokenMapDataDbo;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.ObjectFactory;

/**
 * GENERATED CODE - DO NOT TOUCH
 *
 * Remove this comment to suspend class from generation process.
 */
@Mapper(componentModel = "spring")
public interface DndTokenMapDataDboMapper {
	DndTokenMapData toModel(DndTokenMapDataDbo dbo);

	List<DndTokenMapData> toModels(List<DndTokenMapDataDbo> dbo);

	DndTokenMapDataDbo toDbo(DndTokenMapData model);

	List<DndTokenMapDataDbo> toDbos(List<DndTokenMapData> models);

	@ObjectFactory
	default DndTokenMapData createDndTokenMapData() {
		return new DndTokenMapDataImpl();
	}
}
