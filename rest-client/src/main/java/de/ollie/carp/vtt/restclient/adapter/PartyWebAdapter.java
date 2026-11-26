package de.ollie.carp.vtt.restclient.adapter;

import de.ollie.carp.vtt.core.service.model.Party;
import de.ollie.carp.vtt.core.service.port.web.PartyWebPort;
import de.ollie.carp.vtt.restclient.PartyClient;
import jakarta.inject.Named;
import java.util.UUID;
import lombok.Generated;
import lombok.RequiredArgsConstructor;

/**
 * GENERATED CODE - DO NOT TOUCH
 *
 * Remove this comment to suspend class from generation process.
 */
@Generated
@Named
@RequiredArgsConstructor
public class PartyWebAdapter implements PartyWebPort {

	private final PartyClient client;

	@Override
	public void pushParty(Party toPush) {
		client.updateParty(toPush);
	}

	@Override
	public void deleteParty(UUID idToDelete) {
		client.removeParty(idToDelete);
	}
}
