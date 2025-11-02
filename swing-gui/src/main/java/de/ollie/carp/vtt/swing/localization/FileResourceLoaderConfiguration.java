package de.ollie.carp.vtt.swing.localization;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
class FileResourceLoaderConfiguration {

	@Value(
		"${carp-vtt.resource.file.path-pattern:src/main/resources/localization/carp-vtt-resources-%language%.properties}"
	)
	private String filePathPattern;
}
