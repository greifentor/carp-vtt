package de.ollie.carp.vtt.migration;

import de.ollie.carp.vtt.core.service.TokenService;
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
	private final TokenService tokenService;

	@PostConstruct
	void postConstruct() {
		int page = 0;
		List<ImageDTO> l = imageRestClient.findAllImages(page, 50);
		while (!l.isEmpty()) {
			l
				.stream()
				.filter(img ->
					(img.getTokenTyp() == TokenTypDTO.NPC) ||
					(img.getTokenTyp() == TokenTypDTO.PC) &&
					(img.getImageType() == ImageTypeDTO.ICON)
				)
				.forEach(img -> tokenService.save(toToken(img)));
			System.out.println("Page " + page + " transferred");
			l = imageRestClient.findAllImages(++page, 50);
		}
	}

	private Token toToken(ImageDTO image) {
		return new Token().setId(UUID.fromString(image.getGlobalId())).setImage(image.getImage()).setName(image.getName());
	}
}
