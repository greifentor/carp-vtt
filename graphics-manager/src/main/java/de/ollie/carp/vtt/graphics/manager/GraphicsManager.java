package de.ollie.carp.vtt.graphics.manager;

import de.ollie.carp.vtt.core.service.model.TokenInfoProvider;
import de.ollie.carp.vtt.graphics.manager.model.TokenMap;
import de.ollie.carp.vtt.graphics.manager.model.TokenMap.MapToken;
import de.ollie.carp.vtt.graphics.manager.model.TokenMap.MapTokenId;
import jakarta.inject.Named;
import java.awt.Graphics2D;
import java.awt.image.ImageObserver;
import java.io.IOException;
import java.util.function.BiPredicate;
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
		BiPredicate<MapToken, MapToken> isSelected
	) {
		battleMapDrawer.drawBattleMap(g, mapImage.getImage(), imageObserver);
		for (MapTokenId id : tokens.keySet()) {
			MapToken mapToken = tokens.get(id);
			TokenInfoProvider token = mapToken.getToken();
			TokenInfo ti = tokenInfoMapper.toTokenInfo(token, mapToken.getCoordinates(), mapToken.getCounter());
			try {
				tokenDrawer.drawToken(g, ti, imageObserver);
				if (Boolean.TRUE.equals(isSelected.test(mapToken, selectedToken))) {
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
