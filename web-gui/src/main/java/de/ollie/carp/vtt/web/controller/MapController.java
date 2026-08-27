package de.ollie.carp.vtt.web.controller;

import de.ollie.carp.vtt.restclient.BearerTokenGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

@Controller
@RequiredArgsConstructor
public class MapController {

	private final BearerTokenGenerator bearerTokenGenerator;
	private final RestTemplate restTemplate = new RestTemplate();

	// Der externe REST-Endpunkt, der byte[] liefert
	private static final String IMAGE_SOURCE_URL = "http://localhost:8081/api/v1/images";

	@GetMapping("/")
	public String showImage(Model model) {
		model.addAttribute("imageUrl", "/map");
		return "imageview";
	}

	@GetMapping(value = "/map", produces = MediaType.IMAGE_PNG_VALUE)
	public ResponseEntity<byte[]> getImage() {
		String s =
			"/scenario/{scenarioId}/parties/{partyId}/battlemaps/{battleMapId}".replace(
					"{scenarioId}",
					"60d8b44d-f60f-4b04-b9bb-133b3335db0f"
				)
				.replace("{partyId}", "d95b7312-5669-4ee5-9299-4516034f46d8")
				.replace("{battleMapId}", "5b92a864-6c2e-4d3b-af13-063ab4264fd5");
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "Bearer " + bearerTokenGenerator.create());
		HttpEntity<Void> entity = new HttpEntity<>(headers);
		ResponseEntity<byte[]> response = restTemplate.exchange(IMAGE_SOURCE_URL + s, HttpMethod.GET, entity, byte[].class);
		if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
			return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).body(response.getBody());
		}

		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}
}
