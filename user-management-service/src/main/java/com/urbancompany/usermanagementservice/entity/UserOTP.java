package com.urbancompany.usermanagementservice.entity;

import java.time.LocalDateTime;

public class UserOTP {

	private Long id;
	private Long userId;
	private String email;
	private String otp;
	private LocalDateTime otpGenTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

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

	public LocalDateTime getOtpGenTime() {
		return otpGenTime;
	}

	public void setOtpGenTime(LocalDateTime otpGenTime) {
		this.otpGenTime = otpGenTime;
	}

}
