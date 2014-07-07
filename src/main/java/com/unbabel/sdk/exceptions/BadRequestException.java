package com.unbabel.sdk.exceptions;

public class BadRequestException extends RuntimeException {
	private static final long serialVersionUID = 5679560961395805033L;

	public BadRequestException(String string) {
		super(string);
	}
}
