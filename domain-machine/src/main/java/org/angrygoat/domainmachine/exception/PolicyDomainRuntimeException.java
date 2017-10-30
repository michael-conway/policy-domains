package org.angrygoat.domainmachine.exception;

public class PolicyDomainRuntimeException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3243208307509723136L;

	public PolicyDomainRuntimeException() {
		super();
	}

	public PolicyDomainRuntimeException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}

	public PolicyDomainRuntimeException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public PolicyDomainRuntimeException(String arg0) {
		super(arg0);
	}

	public PolicyDomainRuntimeException(Throwable arg0) {
		super(arg0);
	}

}
