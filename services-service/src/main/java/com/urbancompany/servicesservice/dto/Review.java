package com.urbancompany.servicesservice.dto;

public class Review {

	private String by;
	private Integer rating;
	private String comment;

	public String getBy() {
		return by;
	}

	public void setBy(String by) {
		this.by = by;
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
