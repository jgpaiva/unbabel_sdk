package com.unbabel.sdk.exceptions;

public class MarshalingException extends RuntimeException {
	private static final long serialVersionUID = 7282487627306007198L;

	public MarshalingException(String message, Exception e) {
		super(message, e);
	}

	public MarshalingException(Exception e) {
		super(e);
	}
}
