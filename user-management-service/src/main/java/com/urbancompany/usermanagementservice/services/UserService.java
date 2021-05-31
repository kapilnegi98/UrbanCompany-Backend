package com.urbancompany.usermanagementservice.services;

import com.urbancompany.commonservice.response.BaseResponse;
import com.urbancompany.usermanagementservice.entity.User;
import com.urbancompany.usermanagementservice.request.AddUser;
import com.urbancompany.usermanagementservice.response.FetchUsersReponse;



	public interface UserService {

		public BaseResponse addUser(AddUser user);
		
		public User getUser(String userId);

		public FetchUsersReponse fetchUsers(String ids);
	
}
