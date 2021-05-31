package com.urbancompany.servicesservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Feedback {

	private String id;
	@JsonProperty("for_user")
	private String forUser;
	@JsonProperty("by_user")
	private String byUser;
	private String rating;
	private String comment;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getForUser() {
		return forUser;
	}

	public void setForUser(String forUser) {
		this.forUser = forUser;
	}

	public String getByUser() {
		return byUser;
	}

	public void setByUser(String byUser) {
		this.byUser = byUser;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

}
