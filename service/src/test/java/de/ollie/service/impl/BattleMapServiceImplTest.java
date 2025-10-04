package de.ollie.service.impl;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import de.ollie.service.model.BattleMap;
import de.ollie.service.model.BattleMapId;
import de.ollie.service.port.persistence.BattleMapPersistencePort;
import java.util.Optional;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class BattleMapServiceImplTest {

	@Mock
	private BattleMap battleMap;

	@Mock
	private BattleMapId battleMapId;

	@Mock
	private BattleMapPersistencePort battleMapPersistencePort;

	@InjectMocks
	private BattleMapServiceImpl unitUnderTest;

	@Nested
	class findById_BattleMapId {

		@Test
		void throwsAnException_passingANullValue_asBattleMapId() {
			assertThrows(IllegalArgumentException.class, () -> unitUnderTest.findById(null));
		}

		@Test
		void callsThePersistencePortMethodCorrectly() {
			// Prepare
			when(battleMapPersistencePort.findById(battleMapId)).thenReturn(Optional.of(battleMap));
			// Run & Check
			assertSame(battleMap, unitUnderTest.findById(battleMapId).get());
		}
	}
}
