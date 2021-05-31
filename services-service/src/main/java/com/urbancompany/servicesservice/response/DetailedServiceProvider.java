package com.urbancompany.servicesservice.response;

import java.util.List;

import com.urbancompany.servicesservice.dto.Feedback;
import com.urbancompany.servicesservice.dto.User;
import com.urbancompany.servicesservice.entity.ServiceProvider;

public class DetailedServiceProvider {

	private ServiceProvider serviceProvider;
	private User details;
	private Double avgRating;
	private List<Feedback> reviews;

	public DetailedServiceProvider(ServiceProvider serviceProvider, User details, List<Feedback> reviews,
			Double avgRating) {
		super();
		this.serviceProvider = serviceProvider;
		this.details = details;
		this.reviews = reviews;
		this.avgRating = avgRating;
	}

	public ServiceProvider getServiceProvider() {
		return serviceProvider;
	}

	public void setServiceProvider(ServiceProvider serviceProvider) {
		this.serviceProvider = serviceProvider;
	}

	public Double getAvgRating() {
		return avgRating;
	}

	public void setAvgRating(Double avgRating) {
		this.avgRating = avgRating;
	}

	public List<Feedback> getReviews() {
		return reviews;
	}

	public void setReviews(List<Feedback> reviews) {
		this.reviews = reviews;
	}

	public User getDetails() {
		return details;
	}

	public void setDetails(User details) {
		this.details = details;
	}

}
