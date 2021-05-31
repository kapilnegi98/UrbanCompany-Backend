package com.urbancompany.bookingmanagementservice.entity;

import com.urbancompany.bookingmanagementservice.enums.BookingStatus;

public class Booking {

	private Long id;
	private Long serviceId;
	private Long providerId;
	private Long recieverId;
	private String recieverEmail;
	private Long paymentId;
	private String date;
	private String time;
	private String instructions;
	private BookingStatus status;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getServiceId() {
		return serviceId;
	}

	public void setServiceId(Long serviceId) {
		this.serviceId = serviceId;
	}

	public Long getProviderId() {
		return providerId;
	}

	public void setProviderId(Long providerId) {
		this.providerId = providerId;
	}

	public Long getRecieverId() {
		return recieverId;
	}

	public void setRecieverId(Long recieverId) {
		this.recieverId = recieverId;
	}

	public Long getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(Long paymentId) {
		this.paymentId = paymentId;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getInstructions() {
		return instructions;
	}

	public void setInstructions(String instructions) {
		this.instructions = instructions;
	}

	public BookingStatus getStatus() {
		return status;
	}

	public void setStatus(BookingStatus status) {
		this.status = status;
	}

	public String getRecieverEmail() {
		return recieverEmail;
	}

	public void setRecieverEmail(String recieverEmail) {
		this.recieverEmail = recieverEmail;
	}
	
	

}
