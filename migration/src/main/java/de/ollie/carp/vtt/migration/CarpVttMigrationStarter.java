package de.ollie.carp.vtt.migration;

import lombok.Generated;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Generated
@SpringBootApplication
@ComponentScan("de.ollie.carp.vtt")
@EntityScan("de.ollie.carp.vtt.persistence.jpa")
@EnableJpaRepositories(basePackages = "de.ollie.carp.vtt.persistence.jpa.repository")
public class CarpVttMigrationStarter {

	public static void main(String[] args) {
		System.setProperty("java.awt.headless", "false");
		SpringApplication.run(CarpVttMigrationStarter.class, args);
	}
}
