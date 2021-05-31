package com.urbancompany.bookingmanagementservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ServiceProvider {

	@JsonProperty("service_id")
	private String serviceId;
	@JsonProperty("service_provider_id")
	private String serviceProviderId;
	private String email;
	private String pincode;	
	@JsonProperty("service_type_id")
	private String serviceTypeId;
	private String fees;
	private String comment;

	public String getServiceId() {
		return serviceId;
	}

	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}

	public String getServiceProviderId() {
		return serviceProviderId;
	}

	public void setServiceProviderId(String serviceProviderId) {
		this.serviceProviderId = serviceProviderId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	public String getServiceTypeId() {
		return serviceTypeId;
	}

	public void setServiceTypeId(String serviceTypeId) {
		this.serviceTypeId = serviceTypeId;
	}

	public String getFees() {
		return fees;
	}

	public void setFees(String fees) {
		this.fees = fees;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	@Override
	public String toString() {
		return "ServiceProvider [serviceId=" + serviceId + ", serviceProviderId=" + serviceProviderId + ", email="
				+ email + ", pincode=" + pincode + ", serviceTypeId=" + serviceTypeId + ", fees=" + fees + ", comment="
				+ comment + "]";
	}

}
