package com.urbancompany.usermanagementservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.urbancompany.commonservice.response.BaseResponse;
import com.urbancompany.usermanagementservice.request.GenOTPRequest;
import com.urbancompany.usermanagementservice.request.LogInRequest;
import com.urbancompany.usermanagementservice.response.LogInResponse;
import com.urbancompany.usermanagementservice.services.AuthService;

@RequestMapping("auth")
@RestController
public class AuthController {
	

	@Autowired
	private AuthService authService;

	@PostMapping("otp")
	public ResponseEntity<BaseResponse> generateOTP(@RequestBody GenOTPRequest req) {
		System.out.println("generateOtp controller'");
		return ResponseEntity.ok(authService.generateOTP(req.getEmail()));
	}

	@PostMapping("login")
	public ResponseEntity<LogInResponse> validateOTP(@RequestBody LogInRequest req) {
		System.out.println("inside validate otp");
		return ResponseEntity.ok(authService.userLogIn(req));
	}

}
