package de.ollie.carp.vtt.graphics.manager;

import de.ollie.carp.vtt.core.service.model.CoordinatesInfoProvider;
import de.ollie.carp.vtt.core.service.model.TokenInfoProvider;
import de.ollie.carp.vtt.graphics.manager.model.TokenMap;
import de.ollie.carp.vtt.graphics.manager.model.TokenMap.MapToken;
import jakarta.inject.Named;
import java.awt.Graphics2D;
import java.awt.image.ImageObserver;
import java.io.IOException;
import java.util.function.BiFunction;
import javax.swing.ImageIcon;
import lombok.RequiredArgsConstructor;

@Named
@RequiredArgsConstructor
public class GraphicsManager {

	private final BattleMapDrawer battleMapDrawer;
	private final CounterMarker counterMarker;
	private final SelectedTokenMarker selectedTokenMarker;
	private final TokenDrawer tokenDrawer;
	private final TokenInfoMapper tokenInfoMapper;

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
		battleMapDrawer.drawBattleMap(g, mapImage.getImage(), imageObserver);
		for (MapToken mapToken : tokens.keySet()) {
			TokenInfoProvider token = mapToken.token();
			CoordinatesInfoProvider coordinates = tokens.get(mapToken);
			TokenInfo ti = tokenInfoMapper.toTokenInfo(token, coordinates, mapToken.counter());
			try {
				tokenDrawer.drawToken(g, ti, imageObserver);
				if (Boolean.TRUE.equals(isSelected.apply(mapToken, selectedToken))) {
					selectedTokenMarker.renderSelectedMarker(g, ti);
				}
				if (tokens.hasTokenMoreThanOneTimes(token)) {
					counterMarker.renderCounterMarker(g, ti);
				}
			} catch (IOException ioe) {
				ioe.printStackTrace();
			}
		}
	}
}
