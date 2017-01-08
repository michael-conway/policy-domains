package org.angrygoat.domainmachine.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.shell.Bootstrap;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.StateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineModelConfigurer;
import org.springframework.statemachine.config.model.StateMachineModelFactory;
import org.springframework.statemachine.uml.UmlStateMachineModelFactory;

@EnableStateMachine
@Configuration
public class CoreUMLStateMachine extends
		StateMachineConfigurerAdapter<String, String> {

	private PolicyDomainConfig policyDomainConfig;

	public static final Logger log = LoggerFactory
			.getLogger(CoreUMLStateMachine.class);

	@Override
	public void configure(StateMachineModelConfigurer<String, String> model)
			throws Exception {
		model.withModel().factory(modelFactory());
	}

	public CoreUMLStateMachine() {

	}

	@Bean
	public StateMachineModelFactory<String, String> modelFactory() {

		return new UmlStateMachineModelFactory(
				policyDomainConfig.getModelPath());
	}

	// end::snippetA[]

	public static void main(String[] args) throws Exception {
		Bootstrap.main(args);
	}

	/**
	 * @return the policyDomainConfig
	 */
	public PolicyDomainConfig getPolicyDomainConfig() {
		return policyDomainConfig;
	}

	/**
	 * @param policyDomainConfig
	 *            the policyDomainConfig to set
	 */
	@Autowired
	public void setPolicyDomainConfig(PolicyDomainConfig policyDomainConfig) {
		this.policyDomainConfig = policyDomainConfig;
	}

}
