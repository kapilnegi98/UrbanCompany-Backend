package com.urbancompany.usermanagementservice.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.groups.Default;

public class AddUser {

	@NotBlank(message = "name is mandatory", groups = Default.class)
	private String name;

	@NotBlank(message = "mobile is mandatory", groups = Default.class)
	private String mobile;

	@Email(message = "email is not valid")
	private String email;

	@Pattern(message = "role is not valid", regexp = "^(SERVICE_PROVIDER|SERVICE_RECIEVER)$")
	private String role;

	@NotBlank(message = "pincode is mandatory", groups = Default.class)
	private String pincode;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

}
