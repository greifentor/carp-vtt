package de.ollie.carp.vtt.persistence.jpa.mapper;

import de.ollie.carp.vtt.core.service.model.BattleMap;
import de.ollie.carp.vtt.persistence.jpa.dbo.BattleMapDbo;
import org.mapstruct.Mapper;

/**
 * GENERATED CODE - DO NOT TOUCH
 *
 * Remove this comment to suspend class from generation process.
 */
@Mapper(componentModel = "spring")
public interface BattleMapDboMapper {
	BattleMap toModel(BattleMapDbo dbo);
	BattleMapDbo toDbo(BattleMap model);
}
