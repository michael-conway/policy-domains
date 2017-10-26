/**
 * 
 */
package org.angrygoat.domain.ingest.config;

import java.util.ArrayList;
import java.util.List;

import org.angrygoat.domainmachine.exception.PolicyDomainRuntimeException;
import org.irods.jargon.core.connection.IRODSAccount;
import org.irods.jargon.core.exception.JargonException;
import org.irods.jargon.core.pub.IRODSFileSystem;
import org.irods.jargon.core.pub.RuleProcessingAO;
import org.irods.jargon.core.pub.RuleProcessingAO.RuleProcessingType;
import org.irods.jargon.core.rule.IRODSRuleExecResult;
import org.irods.jargon.core.rule.IRODSRuleParameter;
import org.irods.jargon.core.rule.RuleInvocationConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.action.Action;
import org.springframework.statemachine.config.EnableStateMachineFactory;
import org.springframework.statemachine.config.StateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;
import org.springframework.statemachine.guard.Guard;

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
	private IRODSFileSystem irodsFileSystem;
	
	public IngestDomainConfig() {
		try {
			irodsFileSystem = IRODSFileSystem.instance();
		} catch (JargonException e) {
			log.error("unable to create IRODSFileSystem", e);
			throw new PolicyDomainRuntimeException("unable to create IRODSfileSystem", e);
		}
	}

	@Override
	public void configure(StateMachineStateConfigurer<String, String> states) throws Exception {
		log.info("configure(StateMachineStateConfigurer))");
		states.withStates().initial(IngestStates.READY.toString())
				.state(IngestStates.SIP_DEPOSITED_IN_ARCHIVE.toString(), sipDepositedAction());
				
	}

	@Bean
	public Action<String, String> sipDepositedAction() {
		return new Action<String, String>() {

			@Override
			public void execute(StateContext<String, String> context) {
				log.info("execute of sip deposited");
				log.info("context:{}", context);
				try {
					IRODSAccount irodsAccount = IRODSAccount.instance("localhost", 1247, "test1", "test", "", "zone1", "");
					RuleProcessingAO ruleProcessingAO = irodsFileSystem.getIRODSAccessObjectFactory().getRuleProcessingAO(irodsAccount);
					List<IRODSRuleParameter> inputOverrides = new ArrayList<IRODSRuleParameter>();
					inputOverrides.add(new IRODSRuleParameter("*dataObjectPath", '"' + "foo" + '"'));
					inputOverrides.add(new IRODSRuleParameter("*user", '"' + "user" + '"'));
					inputOverrides.add(new IRODSRuleParameter("*zone", '"' + "zone" + '"'));
					RuleInvocationConfiguration ruleInvocationConfiguration = RuleInvocationConfiguration.instanceWithDefaultAutoSettings(irodsFileSystem.getJargonProperties());
					
					IRODSRuleExecResult result = ruleProcessingAO.executeRuleFromResource("/rules/callIngestPostProcSipDepositInLandingZone.r", inputOverrides, ruleInvocationConfiguration);
					
					log.info("result of call:", result);
				} catch (JargonException e) {
					log.error("unable to trigger rule", e);
					throw new PolicyDomainRuntimeException("unable to trigger proper rule", e);
				} finally {
					irodsFileSystem.closeAndEatExceptions();
				}
				
				
			}
		};
	}

	@Override
	public void configure(StateMachineTransitionConfigurer<String, String> transitions) throws Exception {
		transitions.withExternal().source(IngestStates.READY.toString())
				.target(IngestStates.SIP_DEPOSITED_IN_ARCHIVE.toString())
				.event(IngestEvents.DEPOSIT_SIP_IN_LANDING_AREA.toString());
	}

	@Bean
	public Guard<String, String> guard() {
		return new Guard<String, String>() {

			@Override
			public boolean evaluate(StateContext<String, String> context) {
				log.info("guard for context:{}", context);
				return true;
			}
		};
	}

}
