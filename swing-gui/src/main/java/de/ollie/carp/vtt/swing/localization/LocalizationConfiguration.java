package de.ollie.carp.vtt.swing.localization;

import java.util.List;
import lombok.Generated;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Generated
@Getter
class LocalizationConfiguration {

	// Code by ISO639-3
	static final String ALTERNATIVE_LANGUAGE_DEFAULT = "eng";
	static final String CURRENT_LANGUAGE_DEFAULT = "deu";

	@Value("${carp-vtt.localization.languages:" + CURRENT_LANGUAGE_DEFAULT + "," + ALTERNATIVE_LANGUAGE_DEFAULT + "}")
	private List<String> languages;

	@Setter
	@Value("${carp-vtt.localization.default:" + CURRENT_LANGUAGE_DEFAULT + "}")
	private String currentLanguage;
}
