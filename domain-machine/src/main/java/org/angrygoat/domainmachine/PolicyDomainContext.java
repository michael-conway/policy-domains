/**
 * 
 */
package org.angrygoat.domainmachine;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.statemachine.StateMachine;
import org.springframework.stereotype.Component;

/**
 * Central context object holds state machine, configs. Sub domains will create
 * the state machine and it will be wired into this context object
 * 
 * @author mcc
 *
 */
@Component
public class PolicyDomainContext {

	public static final Logger log = LoggerFactory.getLogger(PolicyDomainContext.class);

	@Autowired
	private StateMachine<String, String> stateMachine;

	/**
	 * @return the stateMachine
	 */
	public StateMachine<String, String> getStateMachine() {
		return stateMachine;
	}

	/**
	 * @param stateMachine
	 *            the stateMachine to set
	 */
	public void setStateMachine(StateMachine<String, String> stateMachine) {
		this.stateMachine = stateMachine;
		log.info("state machine:{}", stateMachine);
	}

}
