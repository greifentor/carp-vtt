package de.ollie.carp.vtt.swing;

import javax.swing.SwingUtilities;
import lombok.Generated;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Generated
@SpringBootApplication
@ComponentScan("de.ollie.carp.vtt")
@EntityScan("de.ollie.carp.vtt.persistence.jpa")
@EnableJpaRepositories(basePackages = "de.ollie.carp.vtt.persistence.jpa.repository")
public class CarpVttSwingStarter {

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(CarpVttSwingStarter.class);
		app.setHeadless(false); // GUI erlauben
		ConfigurableApplicationContext context = app.run(args);
		// GUI aus dem Spring Context holen
		ApplicationFrame mainFrame = context.getBean(ApplicationFrame.class);
		SwingUtilities.invokeLater(mainFrame::showFrame);
	}
}
