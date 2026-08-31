package de.ollie.baselib.util.graphics;

import jakarta.inject.Named;
import java.awt.Image;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import javax.imageio.ImageIO;

@Named
public class ImageFactory {

	public Image create(byte[] imageContent) throws IOException {
		return ImageIO.read(new ByteArrayInputStream(imageContent));
	}
}
