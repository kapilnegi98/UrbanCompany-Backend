package com.urbancompany.paymentservice.service.impl;

import static com.urbancompany.commonservice.Constants.APIKEY_HEADER;
import static com.urbancompany.commonservice.Constants.HTTP;
import static com.urbancompany.commonservice.Constants.Services.BOOKINGS;
import static com.urbancompany.commonservice.Constants.Services.OFFERS;

import java.net.URI;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.urbancompany.commonservice.config.CommonFileProperties;
import com.urbancompany.commonservice.dto.BookingStatusChangeDto;
import com.urbancompany.commonservice.exception.BusinessException;
import com.urbancompany.paymentservice.entity.Payment;
import com.urbancompany.paymentservice.producer.BookingStatusChangeProducer;
import com.urbancompany.paymentservice.repository.PaymentRepository;
import com.urbancompany.paymentservice.request.PaymentRequest;
import com.urbancompany.paymentservice.service.PaymentService;

@Service
public class PaymentServiceImpl implements PaymentService {

	public static final Logger logger = LoggerFactory.getLogger(PaymentServiceImpl.class);
	
	@Autowired
	private CommonFileProperties commonFileProps;
	
	@Autowired
	private WebClient.Builder webClient;

	@Autowired
	private BookingStatusChangeProducer bookingStatusChangeProducer;

	@Autowired
	private PaymentRepository paymentRepository;

	@Override
	public Long pay(PaymentRequest req, String userId, String userMail) {

		validateBooking(req.getBookingId());
		validateOffer(req.getOfferId(), userMail);
		
		Payment payment = new Payment();
		payment.setUserId(Long.valueOf(userId));
		payment.setBookingId(Long.valueOf(req.getBookingId()));
		payment.setModeOfPayment(req.getModeOfPayment());
		payment.setDetails(req.getDetails());
		payment.setAmount(Double.valueOf(req.getAmount()));
		Long paymentId = paymentRepository.save(payment);
		
		changeBookingStatus(req.getBookingId(), paymentId);
		return paymentId;
	}

	private void changeBookingStatus(String bookingId, Long paymentId) {
		BookingStatusChangeDto dto = new BookingStatusChangeDto();
		dto.setBookingId(bookingId);
		dto.setStatus("PROVIDER_CONFIRMATION_PENDING");
		dto.setPaymentId(paymentId.toString());
		bookingStatusChangeProducer.pushBookingStatusChange(dto);
	}

	private void validateOffer(String offerId, String userMail) {
		logger.info("validateOffer() start: for {},{}", offerId, userMail);

		try {
			
			String rsrc = new StringBuilder().append("/uc/offers/v1/offer/").append(offerId)
					.append("?forEmail=").append(userMail).toString();

			String reqURL = new StringBuilder().append(HTTP).append(OFFERS).append(rsrc).toString();

			 webClient.build().head()
					.uri(URI.create(reqURL))
					.header(APIKEY_HEADER, commonFileProps.getApiKey())
					.retrieve().bodyToMono(String.class)
					.doOnError(error -> logger.error("validateOffer() {}: Mono completed with error.", offerId))
					.block();
			

		} catch (Exception e) {
			logger.error("validateOffer() error: for {}", offerId, e);
			throw new BusinessException("offer does not exists");
		}
		
		logger.info("validateOffer() exit: for {}", offerId);
	}

	private void validateBooking(String bookingId) {
		logger.info("validateBooking() start: for {}", bookingId);

		try {
			
			String rsrc = new StringBuilder().append("/uc/booking-management/v1/booking/").append(bookingId).toString();

			String reqURL = new StringBuilder().append(HTTP).append(BOOKINGS).append(rsrc).toString();

			 webClient.build().head()
					.uri(URI.create(reqURL))
					.header(APIKEY_HEADER, commonFileProps.getApiKey())
					.retrieve().bodyToMono(String.class)
					.doOnError(error -> logger.error("validateBooking() {}: Mono completed with error.", bookingId))
					.block();
			

		} catch (Exception e) {
			logger.error("validateBooking() error: for {}", bookingId, e);
			throw new BusinessException("booking does not exists");
		}
		
		logger.info("validateBooking() exit: for {}", bookingId);
		
	}

	@Override
	public Payment findPayment(String paymentId) {
		return paymentRepository.findById(Long.valueOf(paymentId));
	}
	
	
	
}
