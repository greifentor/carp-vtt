package de.ollie.carp.vtt.migration;

import de.ollie.carp.vtt.core.service.MapService;
import de.ollie.carp.vtt.core.service.TokenService;
import de.ollie.carp.vtt.core.service.model.Map;
import de.ollie.carp.vtt.core.service.model.Token;
import de.ollie.carp.vtt.migration.dto.ImageDTO;
import de.ollie.carp.vtt.migration.dto.ImageTypeDTO;
import de.ollie.carp.vtt.migration.dto.TokenTypDTO;
import jakarta.annotation.PostConstruct;
import jakarta.inject.Named;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;

@Named
@RequiredArgsConstructor
class Migrator {

	private final ImageRestClient imageRestClient;
	private final MapService mapService;
	private final TokenService tokenService;

	@PostConstruct
	void postConstruct() {
		int page = 0;
		List<ImageDTO> l = imageRestClient.findAllImages(page, 50);
		while (!l.isEmpty()) {
			l.stream().filter(this::isToMigrate).forEach(this::saveImage);
			System.out.println("Page " + page + " transferred");
			l = imageRestClient.findAllImages(++page, 50);
		}
	}

	private boolean isToMigrate(ImageDTO image) {
		return isToken(image) || isMap(image);
	}

	private boolean isToken(ImageDTO image) {
		return (
			(image.getTokenTyp() == TokenTypDTO.NPC) ||
			(image.getTokenTyp() == TokenTypDTO.PC) &&
			(image.getImageType() == ImageTypeDTO.ICON)
		);
	}

	private boolean isMap(ImageDTO image) {
		return (image.getImageType() == ImageTypeDTO.MAP);
	}

	private void saveImage(ImageDTO image) {
		if (isToken(image)) {
			tokenService.save(toToken(image));
		} else if (isMap(image)) {
			mapService.save(toMap(image));
		}
	}

	private Token toToken(ImageDTO image) {
		return new Token().setId(UUID.fromString(image.getGlobalId())).setImage(image.getImage()).setName(image.getName());
	}

	private Map toMap(ImageDTO image) {
		return new Map().setId(UUID.fromString(image.getGlobalId())).setImage(image.getImage()).setName(image.getName());
	}
}
