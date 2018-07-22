package com.govipul.springboot.rest.startup.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class BookmarkNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -9141261043321678359L;
	private static final String MESSAGE = "Given bookmark: %d is not present in the system.";

	public BookmarkNotFoundException(Long bookmarkId) {
		super(String.format(MESSAGE, bookmarkId));
	}

	public BookmarkNotFoundException(Throwable exception) {
		super(exception);
	}

	public BookmarkNotFoundException(Long bookmarkId, Throwable exception) {
		super(String.format(MESSAGE, bookmarkId), exception);
	}
}
