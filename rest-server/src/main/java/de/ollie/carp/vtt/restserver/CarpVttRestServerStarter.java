package de.ollie.carp.vtt.restserver;

import lombok.Generated;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Generated
@SpringBootApplication
@ComponentScans(
	{ @ComponentScan("de.ollie.carp.vtt.restserver"), @ComponentScan("de.ollie.carp.vtt.graphics.manager") }
)
@EntityScan("de.ollie.carp.vtt.restserver.persistence.jpa.dbo")
@EnableJpaRepositories(basePackages = "de.ollie.carp.vtt.restserver.persistence.jpa.repository")
public class CarpVttRestServerStarter {

	public static void main(String[] args) {
		SpringApplication.run(CarpVttRestServerStarter.class, args);
	}
}
