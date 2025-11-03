package de.ollie.carp.vtt.service.impl;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import de.ollie.carp.vtt.core.service.model.BattleMap;
import de.ollie.carp.vtt.core.service.port.persistence.BattleMapPersistencePort;
import de.ollie.carp.vtt.service.impl.BattleMapServiceImpl;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class BattleMapServiceImplTest {

	@Mock
	private BattleMapPersistencePort battleMapPersistencePort;

	@InjectMocks
	private BattleMapServiceImpl unitUnderTest;

	@Nested
	class save_BattleMap {

		@Test
		void throwsAnException_passingANullValue_asBattleMap() {
			assertThrows(IllegalArgumentException.class, () -> unitUnderTest.save(null));
		}

		@Test
		void callsThePersistencePortMethodCorrectly() {
			// Prepare
			BattleMap saved = mock(BattleMap.class);
			BattleMap toSave = mock(BattleMap.class);
			when(battleMapPersistencePort.update(toSave)).thenReturn(saved);
			// Run & Check
			assertSame(saved, unitUnderTest.save(toSave));
		}
	}
}
