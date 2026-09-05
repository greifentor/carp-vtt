package de.ollie.carp.vtt.graphics.manager.model;

import static de.ollie.baselib.util.Check.ensure;

import de.ollie.carp.vtt.core.service.model.CoordinatesInfoProvider;
import de.ollie.carp.vtt.core.service.model.TokenInfoProvider;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.UUID;
import lombok.Data;

public class TokenMap {

	@Data
	public static class MapToken {

		private CoordinatesInfoProvider coordinates;
		private int counter;
		private UUID id;
		private TokenInfoProvider token;
		private boolean selected;

		public MapToken(TokenInfoProvider token, int counter, UUID id, boolean selected) {
			ensure(counter > 0, "counter cannot be lesser than one!");
			ensure(id != null, "id cannot be null!");
			ensure(token != null, "token cannot be null!");
			this.counter = counter;
			this.id = id;
			this.token = token;
			this.selected = selected;
		}
	}

	private Map<UUID, MapToken> tokens = new HashMap<>();

	public void clear() {
		tokens.clear();
	}

	public MapToken get(UUID key) {
		return tokens.get(key);
	}

	public int getNextCounterFor(TokenInfoProvider token) {
		return (int) (
			tokens
				.entrySet()
				.stream()
				.map(Entry::getValue)
				.filter(mt -> mt.getToken().getId().equals(token.getId()))
				.map(mt -> 1)
				.count() +
			1
		);
	}

	public boolean hasTokenMoreThanOneTimes(TokenInfoProvider token) {
		return (
			tokens
				.entrySet()
				.stream()
				.map(Entry::getValue)
				.filter(mt -> mt.getToken().getId().equals(token.getId()))
				.map(mt -> 1)
				.count() >
			1
		);
	}

	public Set<UUID> keySet() {
		return tokens.keySet();
	}

	public void put(UUID key, MapToken mapToken) {
		ensure(key != null, "key cannot be null!");
		ensure(mapToken != null, "mapToken cannot be null!");
		tokens.put(key, mapToken);
	}

	public void putCoordinates(UUID key, CoordinatesInfoProvider coordinates) {
		ensure(key != null, "key cannot be null!");
		ensure(coordinates != null, "coordinates cannot be null!");
	}

	public String getIds() {
		return tokens.keySet().stream().map(k -> k.toString()).reduce((k0, k1) -> k0 + "," + k1).orElse("");
	}
}
