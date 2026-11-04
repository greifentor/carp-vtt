package de.ollie.carp.vtt.restserver.persistence.jpa.mapper;

import de.ollie.carp.vtt.restserver.core.service.model.User;
import de.ollie.carp.vtt.restserver.persistence.jpa.dbo.UserDbo;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.ObjectFactory;

/**
 * GENERATED CODE - DO NOT TOUCH
 *
 * Remove this comment to suspend class from generation process.
 */
@Mapper(componentModel = "spring")
public interface UserDboMapper {
	User toModel(UserDbo dbo);

	List<User> toModels(List<UserDbo> dbo);

	UserDbo toDbo(User model);

	List<UserDbo> toDbos(List<User> models);
}
