package com.urbancompany.offersservice.request;

import static com.urbancompany.commonservice.Constants.Regex.NUMBER;
import static com.urbancompany.commonservice.Constants.Regex.PERCENTAGE;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class AddOfferRequest {

	@Email(message = "forEmail is not valid")
	private String forEmail;
	
	@Pattern(regexp = PERCENTAGE, message = "discount percentage is invalid")
	private String discount;
	
	@Pattern(regexp = NUMBER, message = "capping is invalid")
	private String capping;
	
	@NotBlank
	private String description;

	public String getForEmail() {
		return forEmail;
	}

	public void setForEmail(String forEmail) {
		this.forEmail = forEmail;
	}

	public String getDiscount() {
		return discount;
	}

	public void setDiscount(String discount) {
		this.discount = discount;
	}

	public String getCapping() {
		return capping;
	}

	public void setCapping(String capping) {
		this.capping = capping;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
