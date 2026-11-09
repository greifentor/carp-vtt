package de.ollie.carp.vtt.swing;

import de.ollie.carp.vtt.core.service.model.Coordinates;
import de.ollie.carp.vtt.core.service.model.CoordinatesImpl;
import jakarta.inject.Named;
import java.math.BigDecimal;

@Named
class CoordinatesToFieldConverter {

	private static final int OFFSET_IN_PIXELS = 25;
	private static final int FIELD_SIZE_IN_PIXELS = 50;

	Coordinates getFieldCoordinates(int x, int y) {
		String fieldX = ((x - OFFSET_IN_PIXELS) / FIELD_SIZE_IN_PIXELS) + ".0";
		String fieldY = ((y - OFFSET_IN_PIXELS) / FIELD_SIZE_IN_PIXELS) + ".0";
		Coordinates coordinates = new CoordinatesImpl();
		coordinates.setFieldX(new BigDecimal(fieldX));
		coordinates.setFieldY(new BigDecimal(fieldY));
		return coordinates;
	}
}
