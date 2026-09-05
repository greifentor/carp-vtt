package de.ollie.carp.vtt.restserver.core.service.model;

import de.ollie.carp.vtt.core.service.model.Coordinates;
import de.ollie.carp.vtt.core.service.model.TokenInfoProvider;
import java.util.UUID;
import lombok.Data;
import lombok.ToString.Exclude;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Data
public class TokenData implements TokenInfoProvider {

	private Coordinates coordinates;
	private int counter;
	private UUID id;
	private UUID tokenId;
	private String name;

	@Exclude
	private byte[] image;

	private de.ollie.carp.vtt.core.service.model.TokenSize tokenSize;
	private boolean selected;
}
