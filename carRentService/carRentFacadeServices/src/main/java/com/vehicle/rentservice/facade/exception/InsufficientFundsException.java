package com.vehicle.rentservice.facade.exception;

public class InsufficientFundsException extends Exception {
	
	private static final long serialVersionUID = -2825751995042606447L;

	public InsufficientFundsException() {
		super();
	}

	public InsufficientFundsException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public InsufficientFundsException(String message, Throwable cause) {
		super(message, cause);
	}

	public InsufficientFundsException(String message) {
		super(message);
	}

	public InsufficientFundsException(Throwable cause) {
		super(cause);
	}
	
}
