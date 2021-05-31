package com.urbancompany.offersservice.repository;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Repository;

import com.urbancompany.offersservice.entity.Offer;

@Repository
public class OfferRepository {
	
	private List<Offer> offers;
	private Long seqId;
	
	@PostConstruct
	public void initialize() {
		offers = new LinkedList<>();
		seqId = 7001l;
		Offer offer = new Offer();
		offer.setForEmail("kapiloct98@gmail.com");
		offer.setId(nextId());
		offer.setCapping(100);
		offer.setDiscount(50);
		offer.setDescription("Get 50% OFF up to 200 on your first booking");
		offers.add(offer);
		Offer offer2 = new Offer();
		offer2.setForEmail("mehtageeta1811@gmail.com");
		offer2.setId(nextId());
		offer2.setCapping(100);
		offer2.setDiscount(10);
		offer2.setDescription("Get 10% OFF up to 200 on your first booking");
		offers.add(offer2);
	}
	
	public Long save(Offer offer) {
		offer.setId(nextId());
		offers.add(offer);
		return offer.getId();
	}
	
	public Offer findById(Long id) {
		return offers.stream().filter(offer -> offer.getId().equals(id)).findAny().orElse(null);
	}

	public Offer findByIdAndForEmail(Long id, String forEmail) {
		return offers.stream().filter(offer -> offer.getId().equals(id) 
				&& offer.getForEmail().equals(forEmail)).findAny().orElse(null);
	}
	
	public List<Offer> findByForEmail(String email) {
		return offers.stream().filter(offer -> offer.getForEmail().equals(email)).collect(Collectors.toList());
	}
	
	public boolean deleteById(Long id) {
		return offers.remove(findById(id));
	}
	
	private Long nextId() {
		return seqId++;
	}
	
	
}
