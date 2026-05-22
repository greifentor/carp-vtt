package de.ollie.carp.vtt.swing;

import static de.ollie.baselib.util.Check.ensure;

import de.ollie.carp.vtt.core.service.model.Coordinates;
import de.ollie.carp.vtt.core.service.model.Token;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class TokenMap {

	record MapToken(Token token, int counter) {
		public MapToken {
			ensure(token != null, "token cannot be null!");
			ensure(counter > 0, "counter cannot be lesser than one!");
		}
	}

	private Map<MapToken, Coordinates> tokens = new HashMap<>();

	public Coordinates get(MapToken key) {
		return tokens.get(key);
	}

	public int getNextCounterFor(Token token) {
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

	public boolean hasTokenMoreThanOneTimes(Token token) {
		return (
			tokens.keySet().stream().filter(mt -> mt.token().getId().equals(token.getId())).mapToInt(mt -> 1).count() > 1
		);
	}

	public Set<MapToken> keySet() {
		return tokens.keySet();
	}

	public void put(MapToken key, Coordinates coordinates) {
		tokens.put(key, coordinates);
	}
}
