package de.ollie.carp.vtt.swing;

import de.ollie.carp.vtt.core.service.model.event.TokenPositionUpdateEvent;
import de.ollie.carp.vtt.core.service.port.web.TokenWebPort;
import jakarta.inject.Named;

// TODO OLI: Remove when real implementation done.
@Named
public class TokenWebDummyAdapter implements TokenWebPort {

	@Override
	public void pushTokenPositionUpdate(TokenPositionUpdateEvent tokenPositionUpdateEvent) {
		System.out.println("push " + tokenPositionUpdateEvent);
	}
}
