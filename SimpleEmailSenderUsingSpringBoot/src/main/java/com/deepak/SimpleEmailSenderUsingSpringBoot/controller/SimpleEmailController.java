package com.deepak.SimpleEmailSenderUsingSpringBoot.controller;


import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/email")
public class SimpleEmailController {
	

	@Autowired
	public JavaMailSender javaMailSender;

	@GetMapping(value = "/sendEmail")
	public String sendEmail() {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo("dpkpal1995@gmail.com");
		message.setSubject("TESTING EMAIL SPRING BOOT DEMO");
		message.setText("checking......");
		javaMailSender.send(message);
		return "sucessfully sent message";
	}

	@GetMapping(value = "/sendEmailAtt")
	public String sendEmailWithAtt() throws MessagingException {
		MimeMessage message = javaMailSender.createMimeMessage();

		MimeMessageHelper helper = new MimeMessageHelper(message, true);

		helper.setTo("dpkpal1995@gmail.com");
		helper.setSubject("TESTING EMAIL SPRING BOOT DEMO");
		helper.setText("with attachment checking......");
		
		ClassPathResource path = new ClassPathResource("Deepak_Resume.pdf");
		helper.addAttachment("Deepak_Resume.pdf", path);
		
		javaMailSender.send(message);
		
		return "sucessfully sent message";
	}
	
	
}
