package com.urbancompany.paymentservice.repository;

import java.util.LinkedList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Repository;

import com.urbancompany.paymentservice.entity.Payment;

@Repository
public class PaymentRepository {

	private List<Payment> payments;
	private Long seqId;
	
	@PostConstruct
	public void initialize() {
		payments = new LinkedList<>();
		seqId = 8001l;
		Payment payment = new Payment();
		payment.setId(8000l);
		payment.setBookingId(6000l);
		payment.setOfferId(7000l);
		payment.setModeOfPayment("DEBIT_CARD");
		payment.setDetails("Card Number -1234-5678-1234");
		payment.setAmount(800d);
		payments.add(payment);
	}
	
	public Long save(Payment payment) {
		payment.setId(nextId());
		payments.add(payment);
		return payment.getId();
	}
	
	public Payment findById(Long id) {
		return payments.stream().filter(payment -> payment.getId().equals(id)).findAny().orElse(null);
	}
	
	private Long nextId() {
		return seqId++;
	}
	
}
