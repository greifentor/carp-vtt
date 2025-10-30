package de.ollie.carp.vtt.swing.localization;

import static de.ollie.baselib.util.Check.ensure;

import jakarta.annotation.PostConstruct;
import jakarta.inject.Named;
import java.util.HashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;

@Named
@RequiredArgsConstructor
class MapResourceManager implements ResourceManager {

	private final LocalizationConfiguration configuration;
	private final Map<String, Map<String, String>> resources = new HashMap<>();

	@PostConstruct
	void postConstruct() {
		configuration.getLanguages().forEach(language -> resources.put(language, new HashMap<>()));
	}

	@Override
	public String getResource(String resourceId) {
		ensure(resourceId != null, "resource id cannot be null!");
		Map<String, String> languageResource = resources.getOrDefault(configuration.getCurrentLanguage(), Map.of());
		return languageResource.getOrDefault(resourceId, resourceId);
	}

	@Override
	public void setResource(String localization, String resourceId, String resource) {
		ensure(localization != null, "localization cannot be null!");
		ensure(resource != null, "resource cannot be null!");
		ensure(resourceId != null, "resource id cannot be null!");
		ensure(
			configuration.getLanguages().contains(localization),
			new IllegalStateException("localization is not contained in languages!")
		);
		Map<String, String> languageResource = resources.get(localization);
		if (languageResource != null) {
			languageResource.put(resourceId, resource);
		}
	}
}
