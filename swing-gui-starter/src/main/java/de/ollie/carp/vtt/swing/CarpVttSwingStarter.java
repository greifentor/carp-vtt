package de.ollie.carp.vtt.swing;

import javax.swing.SwingUtilities;
import lombok.Generated;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@Generated
@SpringBootApplication
@ComponentScan("de.ollie.carp.vtt")
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
