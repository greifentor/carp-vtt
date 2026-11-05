package de.ollie.carp.vtt.restserver.rest.security;

import org.springframework.security.core.GrantedAuthority;

public record CarpVttGrantedAuthority(String authority) implements GrantedAuthority {
	@Override
	public String getAuthority() {
		return authority;
	}
}
