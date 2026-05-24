package de.ollie.carp.vtt.service.impl;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNotSame;

import java.util.UUID;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class UuidServiceImplTest {

	@InjectMocks
	private UuidServiceImpl unitUnderTest;

	@Nested
	class create {

		@Test
		void returnsANewObject() {
			assertNotNull(unitUnderTest.create());
		}

		@Test
		void returnsANewObject_onEachCall() {
			// Run
			UUID uuid0 = unitUnderTest.create();
			UUID uuid1 = unitUnderTest.create();
			// Check
			assertAll(() -> assertNotSame(uuid0, uuid1), () -> assertNotEquals(uuid0, uuid1));
		}
	}
}
