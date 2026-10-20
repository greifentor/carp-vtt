package de.ollie.carp.vtt.restserver.persistence.jpa.mapper;

import de.ollie.carp.vtt.restserver.core.service.model.Party;
import de.ollie.carp.vtt.restserver.persistence.jpa.dbo.PartyDbo;
import java.util.List;
import org.mapstruct.Mapper;

/**
 * GENERATED CODE - DO NOT TOUCH
 *
 * Remove this comment to suspend class from generation process.
 */
@Mapper(componentModel = "spring")
public interface PartyDboMapper {
	Party toModel(PartyDbo dbo);

	List<Party> toModels(List<PartyDbo> dbo);

	PartyDbo toDbo(Party model);

	List<PartyDbo> toDbos(List<Party> models);
}
