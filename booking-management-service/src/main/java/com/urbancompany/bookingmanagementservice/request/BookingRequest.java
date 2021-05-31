package com.urbancompany.bookingmanagementservice.request;

import static com.urbancompany.bookingmanagementservice.common.Constants.DOB_REGEX;
import static com.urbancompany.bookingmanagementservice.common.Constants.TIME_REGEX;
import static com.urbancompany.commonservice.Constants.Regex.NUMBER;

import javax.validation.constraints.Pattern;

public class BookingRequest {

	@Pattern(regexp = NUMBER, message = "service id is invalid")
	private String serviceId;

	@Pattern(regexp = TIME_REGEX, message = "time is invalid")
	private String time;

	@Pattern(regexp = DOB_REGEX, message = "date is invalid")
	private String date;

	private String instructions;

	public String getServiceId() {
		return serviceId;
	}

	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getInstructions() {
		return instructions;
	}

	public void setInstructions(String instructions) {
		this.instructions = instructions;
	}

}
