package com.urbancompany.usermanagementservice.repository;

import java.util.LinkedList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Repository;

import com.urbancompany.usermanagementservice.entity.UserOTP;

@Repository
public class UserOTPRepository {

	private Long seqId;
	private List<UserOTP> otps;
	
	@PostConstruct
	private void initializaRepo() {
		seqId = 2001l;
		otps = new LinkedList<>();
	}
	
	public UserOTP findByEmail(String email) {
		return otps.stream().filter(otp -> otp.getEmail().equals(email)).findFirst().orElse(null);
	}
	
	public UserOTP findByEmailAndOTP(String email, String otp) {
		return otps.stream().filter(userOTP -> userOTP.getEmail().equals(email) && userOTP.getOtp().equals(otp)).findFirst().orElse(null);
	}
	
	public void deleteByEmail(String email) {
		otps.removeIf(userOTP -> userOTP.getEmail().equals(email));
	}

	
	public void save(UserOTP otp) {
		otp.setId(nextId());
		otps.add(otp);
	}

	private Long nextId() {
		return seqId++;
	}
}
