package com.urbancompany.notificationservice.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.urbancompany.notificationservice.service.EmailService;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import com.urbancompany.notificationservice.service.EmailService;

@Service
public class EmailServiceImpl implements EmailService {

	public static final Logger logger = LoggerFactory.getLogger(EmailServiceImpl.class);

    @Autowired
    private JavaMailSender javaMailSender;
	@Override
	public String sendMail(String to, String subject, String messageText) {
		// TODO Auto-generated method stub
		SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(to);

        msg.setSubject(subject);
        msg.setText(messageText);

        javaMailSender.send(msg);
		return "Success";
	}


}
