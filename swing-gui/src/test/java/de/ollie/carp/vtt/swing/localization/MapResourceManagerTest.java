package de.ollie.carp.vtt.swing.localization;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class MapResourceManagerTest {

	private static final String LOCALIZATION = "TL";
	private static final String RESOURCE = "resource";
	private static final String RESOURCE_ID = "resource-id";

	@Mock
	private LocalizationConfiguration configuration;

	@InjectMocks
	private MapResourceManager unitUnderTest;

	@BeforeEach
	void beforeEach() {
		when(configuration.getLanguages()).thenReturn(List.of(LOCALIZATION));
		unitUnderTest.postConstruct();
	}

	@Nested
	class getResource_String {

		@Test
		void throwsAnException_passingANullValue_asResourceId() {
			assertThrows(IllegalArgumentException.class, () -> unitUnderTest.getResource(null));
		}

		@Test
		void returnsTheResourceId_whenNoResourceFound_forPassedResourceId() {
			// Prepare
			when(configuration.getCurrentLanguage()).thenReturn(LOCALIZATION);
			// Run & Check
			assertEquals(RESOURCE_ID, unitUnderTest.getResource(RESOURCE_ID));
		}

		@Test
		void returnsTheResourceId_whenNoResourceFound_causedByMissingResourcesForCurrentLanguage() {
			// Prepare
			when(configuration.getCurrentLanguage()).thenReturn("XX");
			unitUnderTest.setResource(LOCALIZATION, RESOURCE_ID, RESOURCE);
			// Run & Check
			assertEquals(RESOURCE_ID, unitUnderTest.getResource(RESOURCE_ID));
		}

		@Test
		void returnsTheResource_forThePassedResourceId() {
			// Prepare
			when(configuration.getCurrentLanguage()).thenReturn(LOCALIZATION);
			unitUnderTest.setResource(LOCALIZATION, RESOURCE_ID, RESOURCE);
			// Run & Check
			assertEquals(RESOURCE, unitUnderTest.getResource(RESOURCE_ID));
		}
	}

	@Nested
	class setResource_String_String_String {

		@Test
		void throwsAnException_passingANullValue_asLocalization() {
			assertThrows(IllegalArgumentException.class, () -> unitUnderTest.setResource(null, RESOURCE_ID, RESOURCE));
		}

		@Test
		void throwsAnException_passingANullValue_asResource() {
			assertThrows(IllegalArgumentException.class, () -> unitUnderTest.setResource(LOCALIZATION, RESOURCE_ID, null));
		}

		@Test
		void throwsAnException_passingANullValue_asResourceId() {
			assertThrows(IllegalArgumentException.class, () -> unitUnderTest.setResource(LOCALIZATION, null, RESOURCE));
		}

		@Test
		void throwsAnException_passingAnUnknownLocalization() {
			assertThrows(IllegalStateException.class, () -> unitUnderTest.setResource("XX", RESOURCE_ID, RESOURCE));
		}
	}
}
