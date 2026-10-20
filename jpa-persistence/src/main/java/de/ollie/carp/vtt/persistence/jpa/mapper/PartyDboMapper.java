package de.ollie.carp.vtt.persistence.jpa.mapper;

import de.ollie.carp.vtt.core.service.model.Party;
import de.ollie.carp.vtt.core.service.model.PartyImpl;
import de.ollie.carp.vtt.persistence.jpa.dbo.PartyDbo;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.ObjectFactory;

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

	@ObjectFactory
	default Party createParty() {
		return new PartyImpl();
	}
}
