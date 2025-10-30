package de.ollie.carp.vtt.swing.localization;

import static org.junit.jupiter.api.Assertions.assertEquals;

import jakarta.inject.Inject;
import java.util.List;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = { LocalizationConfiguration.class })
class LocalizationConfigurationTest {

	@Inject
	private LocalizationConfiguration unitUnderTest;

	@Nested
	class defaultValuesSet {

		@Test
		void currentLanguageIsSetCorrectly() {
			assertEquals(LocalizationConfiguration.CURRENT_LANGUAGE_DEFAULT, unitUnderTest.getCurrentLanguage());
		}

		@Test
		void languageIsSetCorrectly() {
			// Prepare
			List<String> expected = List.of(
				LocalizationConfiguration.CURRENT_LANGUAGE_DEFAULT,
				LocalizationConfiguration.ALTERNATIVE_LANGUAGE_DEFAULT
			);
			// Run & Check
			assertEquals(expected, unitUnderTest.getLanguages());
		}
	}
}
