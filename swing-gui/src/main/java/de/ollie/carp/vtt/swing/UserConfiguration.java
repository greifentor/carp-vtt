package de.ollie.carp.vtt.swing;

import java.util.UUID;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
public class UserConfiguration {

	@Value("${user.id}")
	private UUID userId;
}
