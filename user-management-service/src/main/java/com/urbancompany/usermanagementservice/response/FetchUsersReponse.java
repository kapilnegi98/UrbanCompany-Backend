package com.urbancompany.usermanagementservice.response;

import java.util.Map;

import com.urbancompany.usermanagementservice.entity.User;

public class FetchUsersReponse {

	private Map<Long, User> users;

	public FetchUsersReponse(Map<Long, User> users) {
		super();
		this.users = users;
	}

	public Map<Long, User> getUsers() {
		return users;
	}

	public void setUsers(Map<Long, User> users) {
		this.users = users;
	}

}
