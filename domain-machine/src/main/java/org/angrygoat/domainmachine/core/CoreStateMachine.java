package org.angrygoat.domainmachine.core;

import java.util.EnumSet;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.shell.Bootstrap;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.StateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineModelConfigurer;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;
import org.springframework.statemachine.config.model.StateMachineModelFactory;

public class CoreStateMachine extends
		StateMachineConfigurerAdapter<String, String> {

	@Override
	public void configure(StateMachineModelConfigurer<String, String> model)
			throws Exception {
		model.withModel().factory(modelFactory());
	}

	@Bean
	public StateMachineModelFactory<String, String> modelFactory() {
		return new StateMachineModelFactory<String, String>(
				"classpath:org/springframework/statemachine/uml/docs/simple-machine.uml");
	}

	public static void main(String[] args) throws Exception {
		Bootstrap.main(args);
	}
}
