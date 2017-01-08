/**
 * 
 */
package org.angrygoat.domainmachine.core;

import org.springframework.stereotype.Component;

/**
 * Policy-domain specific configuration 
 * @author Mike Conway
 * 
 *
 */
@Component
public class PolicyDomainConfig {
	/**
	 * UML description of the state machine for the domain
	 */
	private String modelPath = "file:/home/mconway/Documents/masters/workspace/policy-domains/domain-machine/src/main/papyrus/ingest.uml";

	public PolicyDomainConfig() {
		
	}
	
	/**
	 * @return the modelPath 
	 */
	public String getModelPath() {
		return modelPath;
	}

	/**
	 * @param modelPath the modelPath to set
	 */
	public void setModelPath(String modelPath) {
		this.modelPath = modelPath;
	}

}
