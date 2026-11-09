package de.ollie.carp.vtt.core.service.model;

import java.util.UUID;
import lombok.Generated;

/**
 * GENERATED CODE - DO NOT TOUCH
 *
 * Remove this comment to suspend class from generation process.
 */
@Generated
public interface Party {
	UUID getId();
	String getName();
	RuleSystem getRuleSystem();

	void setId(UUID id);
	void setName(String name);
	void setRuleSystem(RuleSystem ruleSystem);
}
