package com.urbancompany.feedbackservice.repository;

import java.util.LinkedList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Repository;

import com.urbancompany.feedbackservice.entity.Feedback;

@Repository
public class FeedbackRepositoy {

	private Long seqId;
	private List<Feedback> feedbacks;

	@PostConstruct
	public void initialize() {
		seqId = 5001l;
		feedbacks = new LinkedList<>();
		Feedback feedback1 = new Feedback();
		feedback1.setForUser(1002l);
		feedback1.setId(nextId());
		feedback1.setByUser("nikunj.sharma@gmail.com");
		feedback1.setRating(5);
		feedback1.setComment("perferct service! could'nt ask for more");
		Feedback feedback2 = new Feedback();
		feedback2.setForUser(1002l);
		feedback2.setId(nextId());
		feedback2.setByUser("ujuwal.sharma@gmail.com");
		feedback2.setRating(3);
		feedback2.setComment("could have been better. provider was late");
		feedbacks.add(feedback1);
		feedbacks.add(feedback2);
	}
	
	public Long save(Feedback feedback) {
		feedback.setId(nextId());
		feedbacks.add(feedback);
		return feedback.getId();
	}
	
	private Long nextId() {
		return seqId++;
	}
	
	public List<Feedback> getFeedbacks() {
		return feedbacks;
	}
	
}
