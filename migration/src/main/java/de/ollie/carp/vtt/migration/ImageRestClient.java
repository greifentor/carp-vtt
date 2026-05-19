package de.ollie.carp.vtt.migration;

import de.ollie.carp.vtt.migration.dto.ImageDTO;
import jakarta.inject.Named;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@Named
@RequiredArgsConstructor
public class ImageRestClient {

	private final RestTemplate restTemplate;
	private final MigratorClientConfiguration configuration;

	private static final String BASE_URL = "http://localhost:{port}/api/legacy/images";

	public List<ImageDTO> findAllImages(int pageNumber, int pageSize) {
		String url =
			BASE_URL.replace("{port}", "" + configuration.getPort()) + "?pageNumber={pageNumber}&pageSize={pageSize}";
		ResponseEntity<List<ImageDTO>> response = restTemplate.exchange(
			url,
			HttpMethod.GET,
			null,
			new ParameterizedTypeReference<List<ImageDTO>>() {},
			pageNumber,
			pageSize
		);
		return response.getBody();
	}
}
