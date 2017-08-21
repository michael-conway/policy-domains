
package org.angrygoat.domainmachine.core;

import java.util.EnumSet;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.shell.Bootstrap;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;


public class CoreStateMachine<S,E>  {


	//tag::snippetA[]
		@Configuration
		@EnableStateMachine
		static class StateMachineConfig
				extends EnumStateMachineConfigurerAdapter<S, E> {
			
			private AbstractPolicyDomainProvider<S,E> policyDomainProvider;

			@Override
			public void configure(StateMachineStateConfigurer<S, E> states)
					throws Exception {
				states
					.withStates()
						.initial(States.LOCKED)
						.states(EnumSet.allOf(States.class));
			}

			@Override
			public void configure(StateMachineTransitionConfigurer<S, E> transitions)
					throws Exception {
				transitions
					.withExternal()
						.source(States.LOCKED)
						.target(States.UNLOCKED)
						.event(Events.COIN)
						.and()
					.withExternal()
						.source(States.UNLOCKED)
						.target(States.LOCKED)
						.event(Events.PUSH);
			}

		}

		public static void main(String[] args) throws Exception {
			Bootstrap.main(args);
		}
}
