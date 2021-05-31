package com.urbancompany.bookingmanagementservice.producers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.urbancompany.commonservice.dto.NotificationDto;

@Component
public class NotificationProducer {

	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	@Value("${rabbit.notifications.exchange.name}")
	private String exchange;

	@Value("${rabbit.notifications.routingKey.name}")
	private String routingKey;

	private static final Logger logger = LoggerFactory.getLogger(NotificationProducer.class);

	public void sendNotification(NotificationDto dto) {
		logger.info("sendNotificatioin() stated");
		try {

			this.rabbitTemplate.convertAndSend(exchange, routingKey, dto);
		
		} catch (Exception ex) {
			logger.info("sendNotificatioin() error: for {}", dto, ex);
			
		}
	}
}
