/**
 * 
 */
package org.angrygoat.domain.ingest.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.action.Action;
import org.springframework.statemachine.config.EnableStateMachineFactory;
import org.springframework.statemachine.config.StateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;

/**
 * Config and factory for an ingest domain
 * 
 * @author mcc
 *
 */

@Configuration
@EnableStateMachineFactory
public class IngestDomainConfig extends StateMachineConfigurerAdapter<String, String> {

	public static final Logger log = LoggerFactory.getLogger(IngestDomainConfig.class);

	@Override
	public void configure(StateMachineStateConfigurer<String, String> states) throws Exception {
		states.withStates().initial(IngestStates.SIP_DEPOSITED_IN_ARCHIVE.toString())
				.state(IngestStates.SIP_DEPOSITED_IN_ARCHIVE.toString(), sipDepositedAction())
				.state(IngestStates.SIP_DEPOSITED_IN_ARCHIVE.toString());
	}

	@Bean
	public Action<String, String> sipDepositedAction() {
		return new Action<String, String>() {

			@Override
			public void execute(StateContext<String, String> context) {
				log.info("execute of sip deposited");
			}
		};
	}

}
