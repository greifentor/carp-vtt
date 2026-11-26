package de.ollie.carp.vtt.graphics.manager.model;

import static de.ollie.baselib.util.Check.ensure;

import de.ollie.carp.vtt.core.service.model.TokenInfoProvider;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;

public class TokenMap {

	@Data
	@AllArgsConstructor
	public static class MapTokenId {

		private UUID uuid;
	}

	@Data
	public static class MapToken {

		private int counter;
		private BigDecimal fieldX;
		private BigDecimal fieldY;
		private MapTokenId id;
		private TokenInfoProvider token;
		private TokenPaintManager tokenPaintManager;
		private boolean selected;

		public MapToken(
			TokenInfoProvider token,
			int counter,
			MapTokenId id,
			boolean selected,
			TokenPaintManager tokenPaintManager
		) {
			ensure(counter > 0, "counter cannot be lesser than one!");
			ensure(id != null, "id cannot be null!");
			ensure(token != null, "token cannot be null!");
			this.counter = counter;
			this.id = id;
			this.token = token;
			this.tokenPaintManager = (tokenPaintManager != null ? tokenPaintManager : new TokenPaintManager() {});
			this.selected = selected;
		}

		public MapToken(
			TokenInfoProvider token,
			int counter,
			MapTokenId id,
			boolean selected,
			BigDecimal fieldX,
			BigDecimal fieldY,
			TokenPaintManager tokenPaintManager
		) {
			this(token, counter, id, selected, tokenPaintManager);
			this.fieldX = fieldX;
			this.fieldY = fieldY;
		}

		public void setFieldXY(BigDecimal fieldX, BigDecimal fieldY) {
			this.fieldX = fieldX;
			this.fieldY = fieldY;
		}
	}

	private Map<MapTokenId, MapToken> tokens = new HashMap<>();

	public void clear() {
		tokens.clear();
	}

	public MapToken get(MapTokenId key) {
		return tokens.get(key);
	}

	public String getIds() {
		return tokens.keySet().stream().map(MapTokenId::toString).reduce((k0, k1) -> k0 + "," + k1).orElse("");
	}

	public int getNextCounterFor(TokenInfoProvider token) {
		return (
			tokens
				.entrySet()
				.stream()
				.map(Entry::getValue)
				.filter(mt -> mt.getToken().getId().equals(token != null ? token.getId() : null))
				.map(mt -> mt.getCounter())
				.max((i0, i1) -> i0.compareTo(i1))
				.orElse(0) +
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

	public Set<MapTokenId> keySet() {
		return tokens.keySet();
	}

	public void put(MapTokenId key, MapToken mapToken) {
		ensure(key != null, "key cannot be null!");
		ensure(mapToken != null, "mapToken cannot be null!");
		tokens.put(key, mapToken);
	}

	public void putCoordinates(MapTokenId key, BigDecimal fieldX, BigDecimal fieldY) {
		ensure(key != null, "key cannot be null!");
		MapToken mapToken = get(key);
		ensure(mapToken != null, "There is no mapToken for key: " + key);
		mapToken.setFieldXY(fieldX, fieldY);
	}

	public void remove(MapTokenId key) {
		ensure(key != null, "key cannot be null!");
		tokens.remove(key);
	}
}
