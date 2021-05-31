package com.urbancompany.servicesservice.dto;

import java.util.Map;

public class FetchUsersReponse {

	private Map<String, User> users;

	public Map<String, User> getUsers() {
		return users;
	}

	public void setUsers(Map<String, User> users) {
		this.users = users;
	}

}
