package de.ollie.carp.vtt.core.service.model;

import lombok.Generated;
import lombok.Getter;

@Generated
public enum TokenSize {
	TINY(1),
	SMALL(1),
	MEDIUM(1),
	LARGE(2),
	HUGE(3),
	GARGANTUAN(4);

	@Getter
	private int fields;

	private TokenSize(int fields) {
		this.fields = fields;
	}
}
