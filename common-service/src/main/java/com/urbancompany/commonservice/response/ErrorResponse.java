package com.urbancompany.commonservice.response;

import java.util.List;

public class ErrorResponse extends BaseResponse {

	private List<ValidationError> validationErrors;

	public ErrorResponse(String status, String message, List<ValidationError> validationErrors) {
		super(status, message);
		this.validationErrors = validationErrors;
	}

	public List<ValidationError> getValidationErrors() {
		return validationErrors;
	}

	public void setValidationErrors(List<ValidationError> validationErrors) {
		this.validationErrors = validationErrors;
	}
	
	
}
