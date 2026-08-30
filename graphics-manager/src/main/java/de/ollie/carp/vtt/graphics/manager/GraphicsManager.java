package de.ollie.carp.vtt.graphics.manager;

import de.ollie.carp.vtt.core.service.model.CoordinatesInfoProvider;
import de.ollie.carp.vtt.core.service.model.TokenInfoProvider;
import de.ollie.carp.vtt.graphics.manager.model.TokenMap;
import de.ollie.carp.vtt.graphics.manager.model.TokenMap.MapToken;
import jakarta.inject.Named;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.ImageObserver;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.function.BiFunction;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import lombok.RequiredArgsConstructor;

@Named
@RequiredArgsConstructor
public class GraphicsManager {

	private final CounterMarker counterMarker;
	private final SelectedTokenMarker selectedTokenMarker;

	public static final int OFFSET_IN_PIXELS = 12;
	public static final int FIELD_SIZE_IN_PIXELS = 50;

	public void paintBattleMapForScenarioAndParty(
		Graphics2D g,
		TokenMap tokens,
		MapToken selectedToken,
		ImageIcon mapImage,
		ImageObserver imageObserver,
		BiFunction<MapToken, MapToken, Boolean> isSelected
	) {
		g.drawImage(mapImage.getImage(), 0, 0, imageObserver);
		for (MapToken mapToken : tokens.keySet()) {
			TokenInfoProvider token = mapToken.token();
			CoordinatesInfoProvider coordinates = tokens.get(mapToken);
			int x = (coordinates.getFieldX().intValue() * FIELD_SIZE_IN_PIXELS) + OFFSET_IN_PIXELS;
			int y = (coordinates.getFieldY().intValue() * FIELD_SIZE_IN_PIXELS) + OFFSET_IN_PIXELS;
			int height = FIELD_SIZE_IN_PIXELS * token.getTokenSize().getFields();
			int width = FIELD_SIZE_IN_PIXELS * token.getTokenSize().getFields();
			try {
				Image tokenImage = ImageIO.read(new ByteArrayInputStream(token.getImage()));
				g.drawImage(tokenImage, x, y, width, height, imageObserver);
				if (Boolean.TRUE.equals(isSelected.apply(mapToken, selectedToken))) {
					selectedTokenMarker.renderSelectedMarker(g, x, y, width, height);
				}
				if (tokens.hasTokenMoreThanOneTimes(token)) {
					counterMarker.renderCounterMarker(g, x, y, mapToken.counter());
				}
			} catch (IOException ioe) {
				ioe.printStackTrace();
			}
		}
	}
}
