package de.ollie.carp.vtt.restserver.rest;

import de.ollie.carp.vtt.core.service.TokenMapDataService;
import de.ollie.carp.vtt.graphics.manager.GraphicsManager;
import de.ollie.carp.vtt.graphics.manager.model.TokenMap;
import de.ollie.carp.vtt.graphics.manager.model.TokenMap.MapToken;
import de.ollie.carp.vtt.restserver.core.service.BattleMapService;
import de.ollie.carp.vtt.restserver.core.service.PartyService;
import de.ollie.carp.vtt.restserver.core.service.TokenService;
import de.ollie.carp.vtt.restserver.core.service.model.BattleMap;
import de.ollie.carp.vtt.restserver.rest.api.ImageApi;
import java.util.NoSuchElementException;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ImageRestController implements ImageApi {

	private final BattleMapService battleMapService;
	private final GraphicsManager graphicsManager;
	private final PartyService partyService;
	private final TokenService tokenService;
	private final TokenMapDataService tokenDataService;

	@Override
	public ResponseEntity<Resource> getBattleMapRendered(UUID battleMapId, UUID partyId, UUID scenarioId) {
		TokenMap tokenMap = createTokenMap(battleMapId, partyId, scenarioId);
		BattleMap battleMap = battleMapService
			.findById(battleMapId)
			.orElseThrow(() -> new NoSuchElementException("No battle map with id found: " + battleMapId));
		// TokenData selected = tokenDataService.getSelectedToken(battleMapId, partyId,
		// scenarioId);
		// MapToken selectedToken = selected != null
		// ? new MapToken(
		// selected,
		// tokenMap.getNextCounterFor(selected),
		// new MapTokenId(selected.getId()),
		// selected.isSelected(),
		// null
		// )
		// : null;
		try {
			// BufferedImage imageIconBattleMap = ImageIO.read(new
			// ByteArrayInputStream(battleMap.getImage()));
			// graphicsManager.paintBattleMapForScenarioAndParty(
			// (Graphics2D) imageIconBattleMap.getGraphics(),
			// tokenMap,
			// selectedToken,
			// new ImageIcon(imageIconBattleMap),
			// null,
			// this::isSelectedTokenSelected
			// );
			// // Bild serialisieren
			// ByteArrayOutputStream baos = new ByteArrayOutputStream();
			// ImageIO.write(imageIconBattleMap, "png", baos);
			// byte[] imageBytes = baos.toByteArray();
			// // Resource erzeugen
			// ByteArrayResource resource = new ByteArrayResource(imageBytes);
			// return
			// ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).contentLength(imageBytes.length).body(resource);
			return ResponseEntity.ok(null);
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	private boolean isSelectedTokenSelected(MapToken mapToken, MapToken selectedToken) {
		return mapToken.isSelected();
	}

	private TokenMap createTokenMap(UUID battleMapId, UUID partyId, UUID scenarioId) {
		TokenMap tokenMap = new TokenMap();
		//		tokenDataService
		//			.findAllBy(battleMapId, partyId, scenarioId)
		//			.forEach(td -> {
		//				MapTokenId id = new MapTokenId(td.getMapTokenId());
		//				System.out.println("Token-Id: " + td.getId());
		//				de.ollie.carp.vtt.restserver.core.service.model.Token token = tokenService.findById(td.getId()).get();
		//				TokenPaintManager tokenPaintManager = null;
		//				Party party = partyService
		//					.findById(partyId)
		//					.orElseThrow(() -> new NoSuchElementException("No party found with id: " + partyId));
		//				DndTokenBattleMapPartyScenarioData dtbmps = new DndTokenBattleMapPartyScenarioDataImpl();
		//				dtbmps.setCurrentHitPoints(75);
		//				if (party.getRuleSystem() == RuleSystem.DND) {
		//					tokenPaintManager = new DndTokenPaintManager(dtbmps, 75, td.getTokenSize());
		//				}
		//				tokenMap.put(
		//					id,
		//					new MapToken(td, td.getCounter(), id, td.isSelected(), td.getCoordinates(), tokenPaintManager)
		//				);
		//			});
		return tokenMap;
	}
}
