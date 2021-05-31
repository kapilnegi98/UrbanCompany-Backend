package com.urbancompany.feedbackservice.service;

import com.urbancompany.feedbackservice.request.FeedbackRequest;
import com.urbancompany.feedbackservice.response.FetchFeedbacksResponse;

public interface FeedbackService {

	public Long saveFeedback(FeedbackRequest req, String byUser);
	
	public FetchFeedbacksResponse fetchFeedbacks(String forUsers);
}
