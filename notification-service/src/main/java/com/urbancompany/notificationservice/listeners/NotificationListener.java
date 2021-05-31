package com.urbancompany.notificationservice.listeners;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.urbancompany.commonservice.dto.NotificationDto;
import com.urbancompany.notificationservice.service.EmailService;

@Component
public class NotificationListener {
	
	@Autowired
	private EmailService service;

	@RabbitListener(queues = "${rabbit.notifications.queue.name}")
	public void listenNotifications(NotificationDto dto) {
		service.sendMail(dto.getTo(), dto.getSubject(), dto.getBody());
	}
	
}
