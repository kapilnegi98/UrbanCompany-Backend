package com.urbancompany.usermanagementservice.response;

import com.urbancompany.commonservice.response.BaseResponse;
import com.urbancompany.usermanagementservice.entity.User;

public class GetUserResponse extends BaseResponse {

	private User user;
	
	public GetUserResponse(User user) {
		super("success", "user found");
		this.user = user;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
}
