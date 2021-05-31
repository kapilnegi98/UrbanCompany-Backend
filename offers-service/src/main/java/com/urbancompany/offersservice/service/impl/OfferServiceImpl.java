package com.urbancompany.offersservice.service.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.urbancompany.offersservice.entity.Offer;
import com.urbancompany.offersservice.repository.OfferRepository;
import com.urbancompany.offersservice.request.AddOfferRequest;
import com.urbancompany.offersservice.service.OfferService;

@Service
public class OfferServiceImpl implements OfferService {

	@Autowired
	private OfferRepository offersRepo;
	
	public Long addOffer(AddOfferRequest req) {
		Offer offer = new Offer();
		offer.setForEmail(req.getForEmail());
		offer.setDiscount(Integer.valueOf(req.getDiscount()));
		offer.setCapping(Integer.valueOf(req.getCapping()));
		offer.setDescription(req.getDescription());
		return offersRepo.save(offer);
	}
	
	public List<Offer> getOffersForEmail(String email) {
		return offersRepo.findByForEmail(email);
	}
	
	public Offer getOfferById(String id, String forEmail) {
		return StringUtils.isEmpty(forEmail) ? offersRepo.findById(Long.valueOf(id))
				: offersRepo.findByIdAndForEmail(Long.valueOf(id), forEmail);
	}

	@Override
	public boolean deleteOfferById(String id) {
		return offersRepo.deleteById(Long.valueOf(id));
	}
}
