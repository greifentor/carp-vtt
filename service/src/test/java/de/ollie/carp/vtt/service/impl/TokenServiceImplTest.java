package de.ollie.carp.vtt.service.impl;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import de.ollie.carp.vtt.core.service.model.Token;
import de.ollie.carp.vtt.core.service.port.persistence.TokenPersistencePort;
import de.ollie.carp.vtt.service.impl.TokenServiceImpl;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class TokenServiceImplTest {

	@Mock
	private TokenPersistencePort persistencePort;

	@InjectMocks
	private TokenServiceImpl unitUnderTest;

	@Nested
	class save_Token {

		@Test
		void throwsAnException_passingANullValue_asToken() {
			assertThrows(IllegalArgumentException.class, () -> unitUnderTest.save(null));
		}

		@Test
		void callsThePersistencePortMethodCorrectly() {
			// Prepare
			Token saved = mock(Token.class);
			Token toSave = mock(Token.class);
			when(persistencePort.update(toSave)).thenReturn(saved);
			// Run & Check
			assertSame(saved, unitUnderTest.save(toSave));
		}
	}
}
