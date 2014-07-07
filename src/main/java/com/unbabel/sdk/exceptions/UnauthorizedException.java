package com.unbabel.sdk.exceptions;

public class UnauthorizedException extends RuntimeException {
	private static final long serialVersionUID = 4728622267489005744L;

	public UnauthorizedException(String message) {
		super(message);
	}
}
