package de.ollie.carp.vtt.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;

@SpringBootApplication
@ComponentScans(
	{
		@ComponentScan("de.ollie.baselib.util.graphics"),
		@ComponentScan("de.ollie.carp.vtt.web"),
		@ComponentScan("de.ollie.carp.vtt.restclient"),
	}
)
public class ServerTimeApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServerTimeApplication.class, args);
	}
}
