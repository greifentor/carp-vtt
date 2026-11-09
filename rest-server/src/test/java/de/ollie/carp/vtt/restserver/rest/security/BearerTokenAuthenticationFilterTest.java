package de.ollie.carp.vtt.restserver.rest.security;

import static org.junit.jupiter.api.Assertions.assertThrows;

import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class BearerTokenAuthenticationFilterTest {

	@Mock
	private HttpServletRequest request;

	@Mock
	private HttpServletResponse response;

	@Mock
	private FilterChain filterChain;

	@InjectMocks
	private BearerTokenAuthenticationFilter unitUnderTest;

	@Nested
	class doFilterInternal_HttpServletRequest_HttpServletResponse_FilterChain {

		@Disabled
		@Test
		void throwsAnNullPointerException__passingANullValue_asRequest() {
			assertThrows(NullPointerException.class, () -> unitUnderTest.doFilter(null, response, filterChain));
		}
	}
}
