package com.unbabel.sdk.exceptions;

/**
 * Exception for a general error
 * @author jgpaiva
 *
 */
public class ApiException extends RuntimeException {
	private static final long serialVersionUID = -7161461871542928079L;

	public ApiException(String message, Exception e) {
		super(message, e);
	}

	public ApiException(String message) {
		super(message);
	}
}
