package de.ollie.carp.vtt.restserver.rest.security;

import lombok.Generated;
import org.springframework.security.core.GrantedAuthority;

@Generated
public record CarpVttGrantedAuthority(String authority) implements GrantedAuthority {
	@Override
	public String getAuthority() {
		return authority;
	}
}
