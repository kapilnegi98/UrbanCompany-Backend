package com.urbancompany.paymentservice.service;

import com.urbancompany.paymentservice.entity.Payment;
import com.urbancompany.paymentservice.request.PaymentRequest;

public interface PaymentService {

	public Long pay(PaymentRequest payment, String userId, String userMail);
	
	public Payment findPayment(String paymentId);
}
