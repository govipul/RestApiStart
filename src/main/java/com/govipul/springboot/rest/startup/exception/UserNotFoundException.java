package com.govipul.springboot.rest.startup.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -2382711992438039011L;
	private static final String MESSAGE = "The give user:%s doenst exists in the system.";

	public UserNotFoundException(String userName) {
		super(String.format(MESSAGE, userName));
	}

	public UserNotFoundException(Throwable exception) {
		super(exception);
	}

	public UserNotFoundException(String userName, Throwable exception) {
		super(String.format(MESSAGE, userName), exception);
	}

}
