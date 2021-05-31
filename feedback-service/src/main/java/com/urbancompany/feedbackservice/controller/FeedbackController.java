package com.urbancompany.feedbackservice.controller;

import static com.urbancompany.commonservice.Constants.UCRequestAttributes.USER_EMAIL;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.urbancompany.commonservice.response.BaseResponse;
import com.urbancompany.feedbackservice.request.FeedbackRequest;
import com.urbancompany.feedbackservice.response.FetchFeedbacksResponse;
import com.urbancompany.feedbackservice.service.FeedbackService;

@Validated
@RestController
@RequestMapping("feedback")
public class FeedbackController {

	@Autowired
	private FeedbackService feedbackService;
	
	@PostMapping
	public ResponseEntity<BaseResponse> saveFeedback(@RequestBody @Valid FeedbackRequest req,
			@RequestAttribute(USER_EMAIL) String byUser) {
		feedbackService.saveFeedback(req, byUser);
		return ResponseEntity.ok(new BaseResponse("success", "thanks! feedback saved"));
	}
	
	@GetMapping("fetch")
	public ResponseEntity<FetchFeedbacksResponse> fetchFeedbacks(@RequestParam String forUsers) {
		System.out.println("fetch feedbacks");
		return ResponseEntity.ok(feedbackService.fetchFeedbacks(forUsers));
	}
}
