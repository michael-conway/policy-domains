/**
 * 
 */
package org.angrygoat.domain.ingest.functionaltest;

import org.angrygoat.domain.ingest.config.IngestDomainConfig;
import org.angrygoat.domain.ingest.config.IngestStates;
import org.angrygoat.domainmachine.Application;
import org.angrygoat.domainmachine.PolicyDomainContext;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.statemachine.state.State;

import junit.framework.Assert;

/**
 * @author mcc
 *
 */
public class TestBasicIngestMachine {

	private AnnotationConfigApplicationContext context;

	PolicyDomainContext policyDomainContext;

	@Before
	public void setup() {
		context = new AnnotationConfigApplicationContext();
		context.register(IngestDomainConfig.class, Application.class, PolicyDomainContext.class);
		context.refresh();
		policyDomainContext = context.getBean(PolicyDomainContext.class);

	}

	@After
	public void clean() {
		context.close();
		context = null;
	}

	@Test
	public void test() {
		Assert.assertNotNull("no policy domain context found", policyDomainContext);
		Assert.assertNotNull("no state machine in context", policyDomainContext.getStateMachine());
		Assert.assertNotNull("no initial state found", policyDomainContext.getStateMachine().getInitialState());
		State<String, String> state = policyDomainContext.getStateMachine().getState();
		Assert.assertEquals(IngestStates.READY.toString(), state.getId());

	}

}
