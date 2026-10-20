package de.ollie.carp.vtt.migration.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Data
public class ImageDTO {

	private long id;
	private String globalId;
	private int height;
	private byte[] image;
	private ImageFormatDTO imageFormat;
	private ImageTypeDTO imageType;
	private String name;
	private int width;

	private PlaceModeDTO placeMode;
	private TokenTypDTO tokenTyp;

	private DndDataDTO dndDataDto;
}
