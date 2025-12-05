package de.ollie.carp.vtt.legacy.adapter.controller;

import de.ollie.carp.vtt.legacy.adapter.dbo.ImageFormatDBO;
import de.ollie.carp.vtt.legacy.adapter.dbo.ImageTypeDBO;
import de.ollie.carp.vtt.legacy.adapter.dbo.PlaceModeDBO;
import de.ollie.carp.vtt.legacy.adapter.dbo.TokenTypDBO;
import lombok.Data;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Data
public class ImageDTO {

	private long id;
	private String globalId;
	private int height;
	private byte[] image;
	private ImageFormatDBO imageFormat;
	private ImageTypeDBO imageType;
	private String name;
	private int width;

	private PlaceModeDBO placeMode;
	private TokenTypDBO tokenTyp;
}
