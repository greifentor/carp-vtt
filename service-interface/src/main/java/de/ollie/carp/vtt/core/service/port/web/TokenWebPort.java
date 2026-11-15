package de.ollie.carp.vtt.core.service.port.web;

import de.ollie.carp.vtt.core.service.model.Token;
import java.util.List;
import java.util.UUID;
import lombok.Generated;

/**
 * GENERATED CODE - DO NOT TOUCH
 *
 * Remove this comment to suspend class from generation process.
 */
@Generated
public interface TokenWebPort {
	interface SynchronizationObserver {
		void progress(int synced, int total);
	}

	void deleteToken(UUID idToDelete);

	void pushToken(Token toPush);

	void synchronizeTokens(SynchronizationObserver observer, List<Token> tokens);
}
