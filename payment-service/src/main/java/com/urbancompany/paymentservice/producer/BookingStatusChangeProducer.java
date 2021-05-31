package com.urbancompany.paymentservice.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.urbancompany.commonservice.dto.BookingStatusChangeDto;

@Component
public class BookingStatusChangeProducer {

	private static final Logger logger = LoggerFactory.getLogger(BookingStatusChangeProducer.class);

	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	@Value("${rabbit.booking.exchange.name}")
	private String exchange;

	@Value("${rabbit.booking.status.change.routingKey.name}")
	private String routingKey;


	public void pushBookingStatusChange(BookingStatusChangeDto dto) {
		logger.info("pushBookingStatusChange() stated: for {}", dto.getBookingId());
		try {

			this.rabbitTemplate.convertAndSend(exchange, routingKey, dto);
		
		} catch (Exception ex) {
			logger.info("pushBookingStatusChange() error: for {}", dto.getBookingId(), ex);
			
		}
	}
}
