/**
 * 
 */
package org.angrygoat.domainmachine.exception;

/**
 * General parent exception for state machine errors
 * @author mconway
 *
 */
public class PolicyDomainException extends Exception {

	public PolicyDomainException() {
		super();
	}

	public PolicyDomainException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}

	public PolicyDomainException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public PolicyDomainException(String arg0) {
		super(arg0);
	}

	public PolicyDomainException(Throwable arg0) {
		super(arg0);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 2844824121542934573L;

}
