package de.ollie.carp.vtt.core.service.model.event;

import static de.ollie.baselib.util.Check.ensure;

import java.util.UUID;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@EqualsAndHashCode
@Getter
@ToString
public class TokenPositionRemoveEvent {

	@Setter
	private UUID id;

	public TokenPositionRemoveEvent(UUID id) {
		ensure(id != null, "id cannot be null!");
		this.id = id;
	}
}
