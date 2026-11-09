package de.ollie.carp.vtt.restserver.rest.security;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import de.ollie.carp.vtt.restserver.core.service.UserService;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpHeaders;

@ExtendWith(MockitoExtension.class)
class BearerTokenManagerTest {

	@Mock
	private HttpServletRequest request;

	@Mock
	private UserService userService;

	@InjectMocks
	private BearerTokenManager unitUnderTest;

	@Nested
	class hasBearer_HttpServletRequest {

		@Test
		void throwsAnException_passingANullValue_asRequest() {
			assertThrows(IllegalArgumentException.class, () -> unitUnderTest.hasBearer(null));
		}

		@Test
		void returnsFalse_whenRequestHasNoAuthorizationElement() {
			// Prepare
			when(request.getHeader(HttpHeaders.AUTHORIZATION)).thenReturn(null);
			// Run & Check
			assertFalse(unitUnderTest.hasBearer(request));
		}

		@Test
		void returnsFalse_whenRequestHasNoBearerAuthorizationElement() {
			// Prepare
			when(request.getHeader(HttpHeaders.AUTHORIZATION)).thenReturn(";op");
			// Run & Check
			assertFalse(unitUnderTest.hasBearer(request));
		}

		@Test
		void returnsTrue_whenRequestHasABearerAuthorizationElement() {
			// Prepare
			when(request.getHeader(HttpHeaders.AUTHORIZATION)).thenReturn(BearerTokenManager.BEARER_PREFIX + ";op");
			// Run & Check
			assertTrue(unitUnderTest.hasBearer(request));
		}
	}

	@Nested
	class getToken_HttpServletRequest {

		@Test
		void throwsAnException_passingANullValue_asRequest() {
			assertThrows(IllegalArgumentException.class, () -> unitUnderTest.getToken(null));
		}

		@Test
		void returnNull_whenRequestHasNoAuthorizationElement() {
			// Prepare
			when(request.getHeader(HttpHeaders.AUTHORIZATION)).thenReturn(null);
			// Run & Check
			assertNull(unitUnderTest.getToken(request));
		}

		@Test
		void returnsNull_whenRequestHasNoBearerAuthorizationElement() {
			// Prepare
			when(request.getHeader(HttpHeaders.AUTHORIZATION)).thenReturn(";op");
			// Run & Check
			assertNull(unitUnderTest.getToken(request));
		}

		@Test
		void returnsTheToken_whenRequestHasABearerAuthorizationElement() {
			// Prepare
			String token = "token";
			when(request.getHeader(HttpHeaders.AUTHORIZATION)).thenReturn(BearerTokenManager.BEARER_PREFIX + token);
			// Run & Check
			assertEquals(token, unitUnderTest.getToken(request));
		}
	}

	@Nested
	class getUserId_String {

		@Test
		void throwsAnException_passingANullValue_asToken() {
			assertThrows(IllegalArgumentException.class, () -> unitUnderTest.getUserId(null));
		}

		@Test
		void throwsAnException_passingAnInvalidToken() {
			assertThrows(MalformedJwtException.class, () -> unitUnderTest.getUserId(";op"));
		}
	}
}
