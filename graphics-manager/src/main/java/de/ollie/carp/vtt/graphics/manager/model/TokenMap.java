package de.ollie.carp.vtt.graphics.manager.model;

import static de.ollie.baselib.util.Check.ensure;

import de.ollie.carp.vtt.core.service.model.CoordinatesInfoProvider;
import de.ollie.carp.vtt.core.service.model.TokenInfoProvider;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

public class TokenMap {

	public record MapToken(TokenInfoProvider token, int counter, UUID id) {
		public MapToken {
			ensure(id != null, "id cannot be null!");
			ensure(token != null, "token cannot be null!");
			ensure(counter > 0, "counter cannot be lesser than one!");
		}
	}

	private Map<MapToken, CoordinatesInfoProvider> tokens = new HashMap<>();

	public void clear() {
		tokens.clear();
	}

	public CoordinatesInfoProvider get(MapToken key) {
		return tokens.get(key);
	}

	public int getNextCounterFor(TokenInfoProvider token) {
		return (
			tokens
				.keySet()
				.stream()
				.filter(mt -> mt.token().getId().equals(token.getId()))
				.mapToInt(MapToken::counter)
				.max()
				.orElse(0) +
			1
		);
	}

	public boolean hasTokenMoreThanOneTimes(TokenInfoProvider token) {
		return (
			tokens.keySet().stream().filter(mt -> mt.token().getId().equals(token.getId())).mapToInt(mt -> 1).count() > 1
		);
	}

	public Set<MapToken> keySet() {
		return tokens.keySet();
	}

	public void put(MapToken key, CoordinatesInfoProvider coordinates) {
		tokens.put(key, coordinates);
	}

	public String getIds() {
		return tokens.keySet().stream().map(k -> k.id().toString()).reduce((k0, k1) -> k0 + "," + k1).orElse("");
	}
}
