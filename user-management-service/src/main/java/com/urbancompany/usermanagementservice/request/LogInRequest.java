package com.urbancompany.usermanagementservice.request;

import javax.validation.constraints.NotBlank;

public class LogInRequest {

	@NotBlank(message = "email is mandatory")
	private String email;

	@NotBlank(message = "otp is mandatory")
	private String otp;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getOtp() {
		return otp;
	}

	public void setOtp(String otp) {
		this.otp = otp;
	}

}
