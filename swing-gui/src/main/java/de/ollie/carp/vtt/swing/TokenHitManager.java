package de.ollie.carp.vtt.swing;

import static de.ollie.baselib.util.Check.ensure;

import de.ollie.carp.vtt.graphics.manager.model.TokenMap;
import de.ollie.carp.vtt.graphics.manager.model.TokenMap.MapToken;
import de.ollie.carp.vtt.graphics.manager.model.TokenMap.MapTokenId;
import jakarta.inject.Named;
import java.io.IOException;
import lombok.RequiredArgsConstructor;

@Named
@RequiredArgsConstructor
public class TokenHitManager {

	private final TokenHitDetector tokenHitDetector;

	public MapToken getTokenAt(TokenMap tokenMap, int x, int y) {
		ensure(tokenMap != null, "token map cannot be null!");
		for (MapTokenId id : tokenMap.keySet()) {
			MapToken mapToken = tokenMap.get(id);
			try {
				if (tokenHitDetector.hits(mapToken, x, y)) {
					return mapToken;
				}
			} catch (IOException ioe) {
				ioe.printStackTrace();
			}
		}
		return null;
	}
}
