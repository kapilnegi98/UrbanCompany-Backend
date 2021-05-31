package com.urbancompany.notificationservice.service;

public interface EmailService {

	public String sendMail(String to, String subject, String messageText);
}
