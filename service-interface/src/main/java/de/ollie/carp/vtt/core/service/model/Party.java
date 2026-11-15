package de.ollie.carp.vtt.core.service.model;

import java.util.UUID;
import lombok.Generated;

/**
 * GENERATED CODE - DO NOT TOUCH
 *
 * Remove this comment to suspend class from generation process.
 */
@Generated
public interface Party extends OwnerProvider {
	UUID getId();
	String getName();
	RuleSystem getRuleSystem();
	UUID getOwner();

	void setId(UUID id);
	void setName(String name);
	void setRuleSystem(RuleSystem ruleSystem);
	void setOwner(UUID owner);
}
