package de.ollie.carp.vtt.restserver.core.service.configuration;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import de.ollie.carp.vtt.restserver.core.service.model.AccessRight;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class AccessRightConfigurationTest {

	private static final UUID USER_ID = UUID.randomUUID();

	@InjectMocks
	private AccessRightConfiguration unitUnderTest;

	@Nested
	class hasAccess_UUID_AccessRight {

		@Test
		void throwsAnException_passingANullValueAsAccessRight() {
			assertThrows(IllegalArgumentException.class, () -> unitUnderTest.hasAccess(USER_ID, null));
		}

		@Test
		void throwsAnException_passingANullValueAsUserId() {
			assertThrows(IllegalArgumentException.class, () -> unitUnderTest.hasAccess(null, AccessRight.CREATE_OWN));
		}

		@Test
		void returnsFalse_whenNotDataStoredForTheUserId() {
			assertFalse(unitUnderTest.hasAccess(USER_ID, AccessRight.CREATE_OWN));
		}

		@Test
		void returnsFalse_whenThePassedUserRightIsNotStoredForThePassedUserId() {
			// Prepare
			unitUnderTest.setRights(Map.of(USER_ID, List.of(AccessRight.CREATE_PUBLISHED)));
			// Run & Check
			assertFalse(unitUnderTest.hasAccess(USER_ID, AccessRight.CREATE_OWN));
		}

		@Test
		void returnsTrue_whenThePassedUserRightIsStoredForThePassedUserId() {
			// Prepare
			unitUnderTest.setRights(Map.of(USER_ID, List.of(AccessRight.CREATE_OWN)));
			// Run & Check
			assertTrue(unitUnderTest.hasAccess(USER_ID, AccessRight.CREATE_OWN));
		}
	}

	@Nested
	class hasAccess_UUID_AccessRightDotDotDot {

		@Test
		void returnsFalse_passingAnEmptyArrayOfAccessRights() {
			assertFalse(unitUnderTest.hasAccessToAtLeastOneRight(USER_ID));
		}

		@Test
		void throwsAnException_passingANullValueAsUserId() {
			assertThrows(
				IllegalArgumentException.class,
				() -> unitUnderTest.hasAccessToAtLeastOneRight(null, AccessRight.CREATE_OWN)
			);
		}

		@Test
		void returnsFalse_whenNotDataStoredForTheUserId() {
			assertFalse(unitUnderTest.hasAccessToAtLeastOneRight(USER_ID, AccessRight.CREATE_OWN));
		}

		@Test
		void returnsFalse_whenThePassedUserRightIsNotStoredForThePassedUserId() {
			// Prepare
			unitUnderTest.setRights(Map.of(USER_ID, List.of(AccessRight.CREATE_PUBLISHED, AccessRight.DELETE_PUBLISHED)));
			// Run & Check
			assertFalse(unitUnderTest.hasAccessToAtLeastOneRight(USER_ID, AccessRight.CREATE_OWN));
		}

		@Test
		void returnsTrue_whenThePassedUserRightIsStoredForThePassedUserId() {
			// Prepare
			unitUnderTest.setRights(Map.of(USER_ID, List.of(AccessRight.CREATE_OWN)));
			// Run & Check
			assertTrue(
				unitUnderTest.hasAccessToAtLeastOneRight(
					USER_ID,
					AccessRight.RETRIEVE_OWN,
					AccessRight.DELETE_OWN,
					AccessRight.CREATE_OWN
				)
			);
		}
	}
}
