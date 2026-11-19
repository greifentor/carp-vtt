package de.ollie.carp.vtt.restserver.persistence.jpa.mapper;

import de.ollie.carp.vtt.restserver.core.service.model.MapPartyScenario;
import de.ollie.carp.vtt.restserver.persistence.jpa.dbo.MapPartyScenarioDbo;
import java.util.List;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MapPartyScenarioDboMapper {
	List<MapPartyScenario> toModel(List<MapPartyScenarioDbo> dbo);
}
