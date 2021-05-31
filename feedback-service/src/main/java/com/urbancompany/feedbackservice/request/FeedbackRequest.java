package com.urbancompany.feedbackservice.request;

import static com.urbancompany.commonservice.Constants.Regex.NUMBER;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class FeedbackRequest {

	@Pattern(regexp = NUMBER, message = "foruser is invalid")
	private String forUser;

	@Pattern(regexp = "^(1|2|3|4|5)$", message = "rating can only be 1-5")
	private String rating;
	
	@NotBlank(message = "comment is mandatory")
	private String comment;

	public String getForUser() {
		return forUser;
	}

	public void setForUser(String forUser) {
		this.forUser = forUser;
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
