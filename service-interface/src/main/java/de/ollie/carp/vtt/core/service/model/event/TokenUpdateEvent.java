package de.ollie.carp.vtt.core.service.model.event;

import de.ollie.carp.vtt.core.service.model.Token;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@AllArgsConstructor
@EqualsAndHashCode
@Getter
public class TokenUpdateEvent {

	private UUID id;

	private Token token;
}
