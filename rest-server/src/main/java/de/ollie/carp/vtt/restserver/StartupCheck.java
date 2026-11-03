package de.ollie.carp.vtt.restserver;

import jakarta.annotation.PostConstruct;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.util.Arrays;
import javax.sql.DataSource;
import liquibase.integration.spring.SpringLiquibase;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;

@Named
public class StartupCheck {

	@Inject
	private ApplicationContext context;

	@Inject
	private Environment env;

	@PostConstruct
	public void check() {
		String[] beans = context.getBeanNamesForType(liquibase.integration.spring.SpringLiquibase.class);
		System.out.println("\n\nLiquibase Beans: " + Arrays.toString(beans) + "\n\n");
		System.out.println("enabled = " + env.getProperty("spring.liquibase.enabled"));

		System.out.println("change-log = " + env.getProperty("spring.liquibase.change-log"));
		System.out.println("DataSource beans:");
		System.out.println(Arrays.toString(context.getBeanNamesForType(javax.sql.DataSource.class)));
		System.out.println(
			Arrays.toString(context.getBeanNamesForType(liquibase.integration.spring.SpringLiquibase.class))
		);
		ClassPathResource resource = new ClassPathResource("liquibase/rest/change-log-master.xml");
		System.out.println(resource.exists());
		try {
			Class.forName("liquibase.integration.spring.SpringLiquibase");
			System.out.println("Liquibase vorhanden");
		} catch (ClassNotFoundException e) {
			System.out.println("Liquibase fehlt");
		}
		String[] names = context.getBeanDefinitionNames();
		for (String name : names) {
			if (name.toLowerCase().contains("liquibase")) {
				System.out.println(name);
			}
		}
	}

	@Bean
	public SpringLiquibase liquibase(DataSource dataSource) {
		SpringLiquibase liquibase = new SpringLiquibase();
		liquibase.setDataSource(dataSource);
		liquibase.setChangeLog("classpath:liquibase/rest/change-log-master.xml");
		return liquibase;
	}
}
