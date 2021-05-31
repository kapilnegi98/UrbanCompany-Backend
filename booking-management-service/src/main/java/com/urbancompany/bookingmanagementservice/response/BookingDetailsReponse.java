package com.urbancompany.bookingmanagementservice.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.urbancompany.bookingmanagementservice.dto.ServiceProvider;
import com.urbancompany.bookingmanagementservice.dto.User;
import com.urbancompany.bookingmanagementservice.enums.BookingStatus;

@JsonInclude(content = Include.NON_NULL)
public class BookingDetailsReponse {

	private Long id;
	private ServiceProvider service;
	private User provider;
	private User reciever;
	private Long paymentId;
	private String date;
	private String time;
	private String instructions;
	private BookingStatus status;
	private String statusDescription;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ServiceProvider getService() {
		return service;
	}

	public void setService(ServiceProvider service) {
		this.service = service;
	}

	public User getProvider() {
		return provider;
	}

	public void setProvider(User provider) {
		this.provider = provider;
	}

	public User getReciever() {
		return reciever;
	}

	public void setReciever(User reciever) {
		this.reciever = reciever;
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
		this.statusDescription = status.getStatusDescription();
	}

	public String getStatusDescription() {
		return statusDescription;
	}

	public void setStatusDescription(String statusDescription) {
		this.statusDescription = statusDescription;
	}

}
