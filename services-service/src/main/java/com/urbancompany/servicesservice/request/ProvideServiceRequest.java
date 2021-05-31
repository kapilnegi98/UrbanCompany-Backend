package com.urbancompany.servicesservice.request;

import static com.urbancompany.commonservice.Constants.Regex.NUMBER;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class ProvideServiceRequest {

	@Pattern(regexp = NUMBER, message = "service type id must be a number")
	private String serviceTypeId;
	
	@Pattern(regexp = NUMBER, message = "fees is invalid")
	private String fees;
	
	@NotBlank(message = "comment is mandatory")
	private String comment;

	public String getServiceTypeId() {
		return serviceTypeId;
	}

	public void setServiceTypeId(String serviceTypeId) {
		this.serviceTypeId = serviceTypeId;
	}

	public String getFees() {
		return fees;
	}

	public void setFees(String fees) {
		this.fees = fees;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

}
