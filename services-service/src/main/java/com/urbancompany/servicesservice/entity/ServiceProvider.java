package com.urbancompany.servicesservice.entity;

public class ServiceProvider {

	private Long serviceId;
	private Long serviceProviderId;
	private String email;
	private String pincode;
	private Long serviceTypeId;
	private Long fees;
	private String comment;

	public Long getServiceId() {
		return serviceId;
	}

	public void setServiceId(Long id) {
		this.serviceId = id;
	}

	public Long getServiceProviderId() {
		return serviceProviderId;
	}

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	public void setServiceProviderId(Long serviceProviderId) {
		this.serviceProviderId = serviceProviderId;
	}

	public Long getServiceTypeId() {
		return serviceTypeId;
	}

	public void setServiceTypeId(Long serviceTypeId) {
		this.serviceTypeId = serviceTypeId;
	}

	public Long getFees() {
		return fees;
	}

	public void setFees(Long fees) {
		this.fees = fees;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
