package de.ollie.carp.vtt.core.service.model;

import java.math.BigDecimal;
import lombok.Generated;

/**
 * GENERATED CODE - DO NOT TOUCH
 *
 * Remove this comment to suspend class from generation process.
 */
@Generated
public interface Coordinates extends CoordinatesInfoProvider {
	BigDecimal getFieldX();
	BigDecimal getFieldY();

	void setFieldX(BigDecimal fieldX);
	void setFieldY(BigDecimal fieldY);
}
