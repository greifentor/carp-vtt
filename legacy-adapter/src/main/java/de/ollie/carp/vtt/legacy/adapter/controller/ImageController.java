package de.ollie.carp.vtt.legacy.adapter.controller;

import de.ollie.carp.vtt.legacy.adapter.dbo.ImageDBO;
import de.ollie.carp.vtt.legacy.adapter.dbo.ImageTokenDBO;
import de.ollie.carp.vtt.legacy.adapter.repository.ImageDBORepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ImageController.URL)
@RequiredArgsConstructor
class ImageController {

	static final String URL = "/api/legacy/images";

	private final ImageDBORepository repository;

	@GetMapping
	public List<ImageDTO> findAllImages(int pageNumber, int pageSize) {
		return repository
			.findAll(PageRequest.of(pageNumber, pageSize, Sort.by(Direction.ASC, "id")))
			.getContent()
			.stream()
			.map(this::map)
			.toList();
	}

	private ImageDTO map(ImageDBO dbo) {
		ImageDTO dto = new ImageDTO()
			.setGlobalId(dbo.getGlobalId())
			.setHeight(dbo.getHeight())
			.setImage(dbo.getImage())
			.setImageFormat(dbo.getImageFormat())
			.setImageType(dbo.getImageType())
			.setName(dbo.getName())
			.setWidth(dbo.getWidth());
		if (dbo instanceof ImageTokenDBO it) {
			dto.setPlaceMode(it.getPlaceMode()).setTokenTyp(it.getTokenTyp());
		}
		return dto;
	}
}
