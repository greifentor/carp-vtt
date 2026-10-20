package de.ollie.carp.vtt.core.service.model;

import java.util.UUID;
import lombok.Generated;

/**
 * GENERATED CODE - DO NOT TOUCH
 *
 * Remove this comment to suspend class from generation process.
 */
@Generated
public interface Party<T> {
	public UUID getId();

	public String getName();

	public RuleSystem getRuleSystem();

	public T setId(UUID id);

	public T setName(String name);

	public T setRuleSystem(RuleSystem ruleSystem);
}
