package de.ollie.carp.vtt.legacy.adapter.controller;

import de.ollie.carp.vtt.legacy.adapter.dbo.ImageDBO;
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
	public List<ImageDBO> findAllImages(int pageNumber, int pageSize) {
		return repository.findAll(PageRequest.of(pageNumber, pageSize, Sort.by(Direction.ASC, "id"))).getContent();
	}
}
