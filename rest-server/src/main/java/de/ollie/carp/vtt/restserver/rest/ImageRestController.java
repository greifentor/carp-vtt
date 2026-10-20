package de.ollie.carp.vtt.restserver.rest;

import de.ollie.carp.vtt.core.service.model.TokenSize;
import de.ollie.carp.vtt.graphics.manager.GraphicsManager;
import de.ollie.carp.vtt.graphics.manager.dnd.DndTokenPaintManager;
import de.ollie.carp.vtt.graphics.manager.model.TokenMap;
import de.ollie.carp.vtt.graphics.manager.model.TokenMap.MapToken;
import de.ollie.carp.vtt.graphics.manager.model.TokenMap.MapTokenId;
import de.ollie.carp.vtt.graphics.manager.model.TokenPaintManager;
import de.ollie.carp.vtt.restserver.core.service.BattleMapService;
import de.ollie.carp.vtt.restserver.core.service.PartyService;
import de.ollie.carp.vtt.restserver.core.service.TokenDataService;
import de.ollie.carp.vtt.restserver.core.service.TokenService;
import de.ollie.carp.vtt.restserver.core.service.model.BattleMap;
import de.ollie.carp.vtt.restserver.core.service.model.Party;
import de.ollie.carp.vtt.restserver.core.service.model.RuleSystem;
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
	private final PartyService partyService;
	private final TokenService tokenService;
	private final TokenDataService tokenDataService;

	@Override
	public ResponseEntity<Resource> getBattleMapRendered(UUID battleMapId, UUID partyId, UUID scenarioId) {
		TokenMap tokenMap = createTokenMap(battleMapId, partyId, scenarioId);
		BattleMap battleMap = battleMapService
			.findById(battleMapId)
			.orElseThrow(() -> new NoSuchElementException("No battle map with id found: " + battleMapId));
		TokenData selected = tokenDataService.getSelectedToken(battleMapId, partyId, scenarioId);
		MapToken selectedToken = selected != null
			? new MapToken(
				selected,
				tokenMap.getNextCounterFor(selected),
				new MapTokenId(selected.getId()),
				selected.isSelected(),
				null
			)
			: null;
		try {
			BufferedImage imageIconBattleMap = ImageIO.read(new ByteArrayInputStream(battleMap.getImage()));
			graphicsManager.paintBattleMapForScenarioAndParty(
				(Graphics2D) imageIconBattleMap.getGraphics(),
				tokenMap,
				selectedToken,
				new ImageIcon(imageIconBattleMap),
				null,
				this::isSelectedTokenSelected
			);
			// Bild serialisieren
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ImageIO.write(imageIconBattleMap, "png", baos);
			byte[] imageBytes = baos.toByteArray();
			// Resource erzeugen
			ByteArrayResource resource = new ByteArrayResource(imageBytes);
			return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).contentLength(imageBytes.length).body(resource);
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	private boolean isSelectedTokenSelected(MapToken mapToken, MapToken selectedToken) {
		return mapToken.isSelected();
	}

	private TokenMap createTokenMap(UUID battleMapId, UUID partyId, UUID scenarioId) {
		TokenMap tokenMap = new TokenMap();
		tokenDataService
			.findAllBy(battleMapId, partyId, scenarioId)
			.forEach(td -> {
				MapTokenId id = new MapTokenId(td.getMapTokenId());
				System.out.println("Token-Id: " + td.getId());
				de.ollie.carp.vtt.restserver.core.service.model.Token token = tokenService.findById(td.getId()).get();
				TokenPaintManager tokenPaintManager = null;
				Party party = partyService
					.findById(partyId)
					.orElseThrow(() -> new NoSuchElementException("No party found with id: " + partyId));
				if (party.getRuleSystem() == RuleSystem.DND) {
					tokenPaintManager = new DndTokenPaintManager(25, 75, TokenSize.HUGE);
				}
				tokenMap.put(
					id,
					new MapToken(td, td.getCounter(), id, td.isSelected(), td.getCoordinates(), tokenPaintManager)
				);
			});
		return tokenMap;
	}
}
