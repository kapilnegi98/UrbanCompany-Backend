package com.urbancompany.usermanagementservice.services;

import org.springframework.stereotype.Service;

import com.urbancompany.commonservice.response.BaseResponse;
import com.urbancompany.usermanagementservice.request.LogInRequest;
import com.urbancompany.usermanagementservice.response.LogInResponse;

@Service
public interface AuthService {
	public BaseResponse generateOTP(String mobile);

	public LogInResponse userLogIn(LogInRequest req);
}
