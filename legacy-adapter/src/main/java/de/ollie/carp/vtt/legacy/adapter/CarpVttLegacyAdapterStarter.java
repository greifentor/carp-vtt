package de.ollie.carp.vtt.legacy.adapter;

import lombok.Generated;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Generated
@SpringBootApplication
@ComponentScan("de.ollie.carp.vtt.legacy.adapter")
@EntityScan("de.ollie.carp.vtt.legacy.adapter")
@EnableJpaRepositories(basePackages = "de.ollie.carp.vtt.legacy.adapter")
public class CarpVttLegacyAdapterStarter {

	public static void main(String[] args) {
		SpringApplication.run(CarpVttLegacyAdapterStarter.class, args);
	}
}
