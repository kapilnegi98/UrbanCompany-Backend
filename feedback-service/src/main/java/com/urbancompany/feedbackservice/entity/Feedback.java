package com.urbancompany.feedbackservice.entity;

public class Feedback {

	private Long id;
	private Long forUser;
	private String byUser;
	private Integer rating;
	private String comment;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getForUser() {
		return forUser;
	}

	public void setForUser(Long forUser) {
		this.forUser = forUser;
	}

	public String getByUser() {
		return byUser;
	}

	public void setByUser(String byUser) {
		this.byUser = byUser;
	}

	public Integer getRating() {
		return rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

}
