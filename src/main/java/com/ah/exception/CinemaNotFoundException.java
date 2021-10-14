package com.ah.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "No staff has this ID")
public class CinemaNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -6518911528442869118L;

	public CinemaNotFoundException() {
		super();
	}

	public CinemaNotFoundException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public CinemaNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public CinemaNotFoundException(String message) {
		super(message);
	}

	public CinemaNotFoundException(Throwable cause) {
		super(cause);
	}
}
