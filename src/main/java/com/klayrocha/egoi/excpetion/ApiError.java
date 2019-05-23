package com.klayrocha.egoi.excpetion;

import org.springframework.http.HttpStatus;

/**
 * Class with error information
 * 
 * @author Francis Klay Rocha
 *
 */
public class ApiError {
	private HttpStatus status;
	private String message;

	ApiError(HttpStatus status, String message, Throwable ex) {
		this.status = status;
		this.message = message;
	}

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
