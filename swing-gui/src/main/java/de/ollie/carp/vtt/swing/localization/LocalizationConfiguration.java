package de.ollie.carp.vtt.swing.localization;

import java.util.List;
import lombok.Generated;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Generated
@Getter
class LocalizationConfiguration {

	static final String ALTERNATIVE_LANGUAGE_DEFAULT = "EN";
	static final String CURRENT_LANGUAGE_DEFAULT = "DE";

	@Value("${carp-vtt.localization.languages:" + CURRENT_LANGUAGE_DEFAULT + "," + ALTERNATIVE_LANGUAGE_DEFAULT + "}")
	private List<String> languages;

	@Value("${carp-vtt.localization.default:" + CURRENT_LANGUAGE_DEFAULT + "}")
	private String currentLanguage;
}
