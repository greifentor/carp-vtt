package de.ollie.carp.vtt.restserver.persistence.jpa.mapper;

import de.ollie.carp.vtt.restserver.core.service.model.BattleMap;
import de.ollie.carp.vtt.restserver.persistence.jpa.dbo.BattleMapDbo;
import java.util.List;
import org.mapstruct.Mapper;

/**
 * GENERATED CODE - DO NOT TOUCH
 *
 * Remove this comment to suspend class from generation process.
 */
@Mapper(componentModel = "spring")
public interface BattleMapDboMapper {
	BattleMap toModel(BattleMapDbo dbo);

	List<BattleMap> toModels(List<BattleMapDbo> dbo);

	BattleMapDbo toDbo(BattleMap model);

	List<BattleMapDbo> toDbos(List<BattleMap> models);
}
