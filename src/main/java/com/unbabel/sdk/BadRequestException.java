package com.unbabel.sdk;

public class BadRequestException extends RuntimeException {
	private static final long serialVersionUID = 5679560961395805033L;

	public BadRequestException(Exception e) {
		super(e);
	}
}
