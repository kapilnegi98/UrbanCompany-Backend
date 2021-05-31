package com.urbancompany.offersservice.service;

import java.util.List;

import com.urbancompany.offersservice.entity.Offer;
import com.urbancompany.offersservice.request.AddOfferRequest;

public interface OfferService {

	public Long addOffer(AddOfferRequest req);

	public List<Offer> getOffersForEmail(String email);

	public Offer getOfferById(String id, String forEmail);

	public boolean deleteOfferById(String id);
	
}
