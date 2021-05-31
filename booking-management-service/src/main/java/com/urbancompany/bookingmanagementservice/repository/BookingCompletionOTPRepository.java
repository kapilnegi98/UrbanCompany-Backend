package com.urbancompany.bookingmanagementservice.repository;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Repository;

@Repository
public class BookingCompletionOTPRepository {

	private Map<Long,String> otps;
	
	@PostConstruct
	public void initialize() {
		otps = new HashMap<>();
	}
	
	public void save(Long bookingId, String otp) {
		 otps.put(bookingId, otp);
	}
	
	public String getOTP(Long bookingId) {
		return otps.get(bookingId);
	}
}
