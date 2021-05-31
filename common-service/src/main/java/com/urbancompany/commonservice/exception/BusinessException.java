package com.urbancompany.commonservice.exception;

public class BusinessException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public BusinessException(String exception) {
		super(exception);
		
		
	}
	
	public BusinessException(String exception, Throwable cause) {
		super(exception, cause);
	}
}




