package de.ollie.carp.vtt.migration.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Generated;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Data
@Generated
@Entity(name = "Image")
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "IMAGE")
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
}
