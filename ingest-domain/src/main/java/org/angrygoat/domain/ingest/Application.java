/**
 * 
 */
package org.angrygoat.domain.ingest;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.config.EnableIntegration;

/**
 * Core domain machine application
 * 
 * @author mcc
 *
 */
@SpringBootApplication
@ComponentScan
@EnableIntegration
@IntegrationComponentScan
public class Application {

	public static final Logger log = LoggerFactory.getLogger(Application.class);

	public static void main(String[] args) throws IOException {
		ConfigurableApplicationContext ctx = new SpringApplication(Application.class).run(args);
		System.out.println("Hit Enter to terminate");
		System.in.read();
		ctx.close();
		// Bootstrap.main(args);

	}

}
