package com.urbancompany.commonservice.exception;

public class TechnicalException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public TechnicalException(String exception) {
		super(exception);
	}
	
	public TechnicalException(String exception, Throwable cause) {
		super(exception, cause);
	}

}
