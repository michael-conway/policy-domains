/**
 * 
 */
package org.angrygoat.domainmachine.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.statemachine.config.StateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineModelConfigurer;
import org.springframework.statemachine.config.model.StateMachineModelFactory;
import org.springframework.statemachine.uml.UmlStateMachineModelFactory;

/**
 * Reads in a given UML model to create a state machine configuration
 * @author mconway
 *
 */
public class DomainUmlModelConfig extends StateMachineConfigurerAdapter<String, String> {
	private String modelPath;
	public String getModelPath() {
		return modelPath;
	}

	public void setModelPath(String modelPath) {
		this.modelPath = modelPath;
	}

	public static final Logger log = LoggerFactory
			.getLogger(DomainUmlModelConfig.class);
	
	@Override
    public void configure(StateMachineModelConfigurer<String, String> model) throws Exception {
        model
            .withModel()
                .factory(modelFactory());
    }
	
	public DomainUmlModelConfig(final String modelPath) {
		if (modelPath == null || modelPath.isEmpty()) {
			throw new IllegalArgumentException("null or empty modelPath");
		}
		log.info("modelPath:{}", modelPath);
		this.modelPath = modelPath;
	}

	@Bean
    public StateMachineModelFactory<String, String> modelFactory() {
		if (modelPath == null || modelPath.isEmpty()) {
			throw new IllegalStateException("no model path configured");
		}
        return new UmlStateMachineModelFactory(modelPath);
    }

}
