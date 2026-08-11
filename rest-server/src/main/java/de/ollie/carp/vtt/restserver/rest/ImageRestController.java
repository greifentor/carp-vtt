package de.ollie.carp.vtt.restserver.rest;

import de.ollie.carp.vtt.restserver.rest.api.ImageApi;
import java.util.UUID;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;

public class ImageRestController implements ImageApi {

	@Override
	public ResponseEntity<Resource> getBattleMapRendered(UUID battleMapId, UUID partyId, UUID scenarioId) {
		// TODO Auto-generated method stub
		return null;
	}
}
