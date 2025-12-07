package de.ollie.carp.vtt.persistence.jpa.mapper;

import de.ollie.carp.vtt.core.service.model.Map;
import de.ollie.carp.vtt.persistence.jpa.dbo.MapDbo;
import org.mapstruct.Mapper;

/**
 * GENERATED CODE - DO NOT TOUCH
 *
 * Remove this comment to suspend class from generation process.
 */
@Mapper(componentModel = "spring")
public interface MapDboMapper {
	Map toModel(MapDbo dbo);
	MapDbo toDbo(Map model);
}
