package de.ollie.carp.vtt.core.service.model;

import java.math.BigDecimal;
import lombok.Generated;

/**
 * GENERATED CODE - DO NOT TOUCH
 *
 * Remove this comment to suspend class from generation process.
 */
@Generated
public interface Coordinates<T> extends CoordinatesInfoProvider {
	public BigDecimal getFieldX();

	public BigDecimal getFieldY();

	public T setFieldX(BigDecimal fieldX);

	public T setFieldY(BigDecimal fieldY);
}
