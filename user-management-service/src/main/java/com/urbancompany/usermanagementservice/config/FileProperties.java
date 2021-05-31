package com.urbancompany.usermanagementservice.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class FileProperties {

	@Value("${user.otp.validity}")
	private String otpValidity;

	public String getOtpValidity() {
		return otpValidity;
	}


	
}
