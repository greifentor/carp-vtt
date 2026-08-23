package de.ollie.carp.vtt.restserver.rest;

import de.ollie.carp.vtt.graphics.manager.GraphicsManager;
import de.ollie.carp.vtt.graphics.manager.model.TokenMap;
import de.ollie.carp.vtt.graphics.manager.model.TokenMap.MapToken;
import de.ollie.carp.vtt.restserver.core.service.BattleMapService;
import de.ollie.carp.vtt.restserver.core.service.TokenDataService;
import de.ollie.carp.vtt.restserver.core.service.TokenPositionService;
import de.ollie.carp.vtt.restserver.core.service.model.BattleMap;
import de.ollie.carp.vtt.restserver.core.service.model.TokenData;
import de.ollie.carp.vtt.restserver.rest.api.ImageApi;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.NoSuchElementException;
import java.util.UUID;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ImageRestController implements ImageApi {

	private final BattleMapService battleMapService;
	private final GraphicsManager graphicsManager;
	private final TokenPositionService tokenPositionService;
	private final TokenDataService tokenDataService;

	@Override
	public ResponseEntity<Resource> getBattleMapRendered(UUID battleMapId, UUID partyId, UUID scenarioId) {
		TokenMap tokenMap = createTokenMap(battleMapId, partyId, scenarioId);
		BattleMap battleMap = battleMapService
			.findById(battleMapId)
			.orElseThrow(() -> new NoSuchElementException("No battle map with id found: " + battleMapId));
		TokenData selected = tokenDataService.getSelectedToken(battleMapId, partyId, scenarioId);
		MapToken selectedToken = selected != null
			? new MapToken(selected, tokenMap.getNextCounterFor(selected), selected.getId())
			: null;
		try {
			BufferedImage imageIconBattleMap = ImageIO.read(new ByteArrayInputStream(battleMap.getImage()));
			graphicsManager.paintBattleMapForScenarioAndParty(
				(Graphics2D) imageIconBattleMap.getGraphics(),
				tokenMap,
				selectedToken,
				new ImageIcon(imageIconBattleMap),
				null
			);
			// Bild serialisieren
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ImageIO.write(imageIconBattleMap, "png", baos);
			byte[] imageBytes = baos.toByteArray();
			// Resource erzeugen
			ByteArrayResource resource = new ByteArrayResource(imageBytes);
			return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).contentLength(imageBytes.length).body(resource);
		} catch (Exception e) {
			System.out.println("\n\n" + e.getMessage() + "\n\n");
			throw new RuntimeException(e.getMessage());
		}
	}

	private TokenMap createTokenMap(UUID battleMapId, UUID partyId, UUID scenarioId) {
		TokenMap tokenMap = new TokenMap();
		tokenDataService
			.findAllBy(battleMapId, partyId, scenarioId)
			.forEach(td -> tokenMap.put(new MapToken(td, tokenMap.getNextCounterFor(td), td.getId()), td.getCoordinates()));
		return tokenMap;
	}
}
