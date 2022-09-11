package com.garden.helper.exceptions;

public class SecurityPrincipalInvalidException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SecurityPrincipalInvalidException() {
		super();
	}

	public SecurityPrincipalInvalidException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public SecurityPrincipalInvalidException(String message, Throwable cause) {
		super(message, cause);
	}

	public SecurityPrincipalInvalidException(String message) {
		super(message);
	}

	public SecurityPrincipalInvalidException(Throwable cause) {
		super(cause);
	}

}
