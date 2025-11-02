package de.ollie.carp.vtt.swing.localization;

import static de.ollie.baselib.util.Check.ensure;

import jakarta.inject.Named;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
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
		try {
			Files
				.readAllLines(Path.of(configuration.getFilePathPattern().replace("%language%", language)))
				.stream()
				.filter(line -> !line.isEmpty())
				.forEach(line ->
					resourceManagerToLoad.setResource(
						language,
						line.substring(0, line.indexOf("=") - 1).trim(),
						line.substring(line.indexOf("=") + 1).trim()
					)
				);
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}
}
