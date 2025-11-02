package de.ollie.carp.vtt.swing.localization;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class FileResourceLoaderTest {

	@Mock
	private FileResourceLoaderConfiguration configuration;

	@Mock
	private LocalizationConfiguration localizationConfiguration;

	@InjectMocks
	private FileResourceLoader unitUnderTest;

	@Nested
	class loadResources_ResourceManager {

		@Mock
		private ResourceManager resourceManager;

		@Test
		void throwsAnException_passingANullValue_asResourceManager() {
			assertThrows(IllegalArgumentException.class, () -> unitUnderTest.loadResources(null));
		}

		@Test
		void throwsAnException_whenLocalizationConfiguration_isMissing() {
			// Prepare
			when(localizationConfiguration.getLanguages()).thenReturn(List.of());
			// Run & Check
			assertThrows(IllegalStateException.class, () -> unitUnderTest.loadResources(resourceManager));
		}

		@Test
		void loadsCorrectTestResources_forLanguageQuenya() {
			// Prepare
			when(configuration.getFilePathPattern())
				.thenReturn("src/test/resources/localization/carp-vtt-resources-%language%.properties");
			when(localizationConfiguration.getLanguages()).thenReturn(List.of("qya", "tlh"));
			// Run
			unitUnderTest.loadResources(resourceManager);
			// Check
			verify(resourceManager, times(1)).setResource("qya", "a.resource.identifier", "the qya resource");
			verify(resourceManager, times(1)).setResource("qya", "another.resource.identifier", "another qya resource");
		}

		@Test
		void loadsCorrectTestResources_forLanguageTlinganHol() {
			// Prepare
			when(configuration.getFilePathPattern())
				.thenReturn("src/test/resources/localization/carp-vtt-resources-%language%.properties");
			when(localizationConfiguration.getLanguages()).thenReturn(List.of("qya", "tlh"));
			// Run
			unitUnderTest.loadResources(resourceManager);
			// Check
			verify(resourceManager, times(1)).setResource("tlh", "a.resource.identifier", "the tlh resource");
		}
	}
}
