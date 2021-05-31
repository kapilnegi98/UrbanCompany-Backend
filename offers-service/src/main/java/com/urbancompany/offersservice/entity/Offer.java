package com.urbancompany.offersservice.entity;

public class Offer {

	private Long id;
	private String forEmail;
	private Integer discount;
	private Integer capping;
	private String description;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getForEmail() {
		return forEmail;
	}

	public void setForEmail(String forEmail) {
		this.forEmail = forEmail;
	}

	public Integer getDiscount() {
		return discount;
	}

	public void setDiscount(Integer discount) {
		this.discount = discount;
	}

	public Integer getCapping() {
		return capping;
	}

	public void setCapping(Integer capping) {
		this.capping = capping;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
