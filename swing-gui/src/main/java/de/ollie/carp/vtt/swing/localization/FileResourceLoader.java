package de.ollie.carp.vtt.swing.localization;

import static de.ollie.baselib.util.Check.ensure;

import jakarta.inject.Named;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import lombok.RequiredArgsConstructor;

@Named
@RequiredArgsConstructor
class FileResourceLoader implements ResourceLoader {

	private final FileResourceLoaderConfiguration configuration;
	private final LocalizationConfiguration localizationConfiguration;

	@Override
	public void loadResources(ResourceManager resourceManagerToLoad) {
		ensure(resourceManagerToLoad != null, "resource manager to load cannot be null!");
		ensure(
			!localizationConfiguration.getLanguages().isEmpty(),
			() -> new IllegalStateException("there are no languages configured!")
		);
		localizationConfiguration
			.getLanguages()
			.forEach(language -> loadResourcesFromFile(language, resourceManagerToLoad));
	}

	private void loadResourcesFromFile(String language, ResourceManager resourceManagerToLoad) {
		try (FileReader fr = new FileReader(configuration.getFilePathPattern().replace("%language%", language))) {
			Properties p = new Properties();
			p.load(fr);
			p.entrySet().forEach(e -> resourceManagerToLoad.setResource(language, "" + e.getKey(), "" + e.getValue()));
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}
}
