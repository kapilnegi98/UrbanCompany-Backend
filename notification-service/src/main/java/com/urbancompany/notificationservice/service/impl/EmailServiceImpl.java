package com.urbancompany.notificationservice.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.urbancompany.notificationservice.service.EmailService;

//import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
//import com.sendgrid.Method;
//import com.sendgrid.Request;
//import com.sendgrid.Response;
//import com.sendgrid.SendGrid;
//import com.sendgrid.helpers.mail.Mail;
//import com.sendgrid.helpers.mail.objects.Content;
//import com.sendgrid.helpers.mail.objects.Email;
//import com.urbancompany.notificationservice.service.EmailService;

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

//	@Override
//	@HystrixCommand(fallbackMethod = "sendMailFallback")
//	public String sendMail(String to, String subject, String body) {
//		logger.info("sendMail() started: to {}", to);

//		Email from = new Email("kapil1232123322.m@gmail.com");
//	    Email toEmail = new Email(to);
//	    Content content = new Content("text/plain", body);
//	    Mail mail = new Mail(from, subject, toEmail, content);
//
//	    SendGrid sg = new SendGrid("SG.pSMuGFwFGHFHGGJG6wMc4CiSKkMAcFsKf5OCj9SVSf4ueFi8s");
//	    Request request = new Request();
//	    try {
//	      request.setMethod(Method.POST);
//	      request.setEndpoint("mail/send");
//	      request.setBody(mail.build());
//	      Response response = sg.api(request);
//	      logger.info("sendMail(): status code {}", response.getStatusCode());
//	    } catch (IOException ex) {
//	      logger.error("sendMail() error", ex);
//	      throw new RuntimeException();
//	    }
//		
//	    return "Success";
//	}
//	
//	public String sendMailFallback(String to, String subject, String body) {
//		logger.warn("sendMailFallback() started: to {}", to);
//		return "Failure";
//	}
}
