package com.urbancompany.servicesservice.dto;

import java.util.List;
import java.util.Map;

public class FetchFeedbackResponse {

	private Map<String, List<Feedback>> feedbacks;

	public Map<String, List<Feedback>> getFeedbacks() {
		return feedbacks;
	}

	public void setFeedbacks(Map<String, List<Feedback>> feedbacks) {
		this.feedbacks = feedbacks;
	}

}
