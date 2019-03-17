package com.usa.ri.gov.ies.util;

import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component("emailUtil")
public class EmailUtil{
	Logger logger= LoggerFactory.getLogger(EmailUtil.class);
	@Autowired(required=true)
	public JavaMailSender mailSender;
	
	public void sendEmail(String to,String subject,String body) {
		logger.debug("EmailUtil: sendMail() started");
		MimeMessage message= mailSender.createMimeMessage();
		MimeMessageHelper helper;
		try {
			helper= new MimeMessageHelper(message,true);
			helper.setTo(to);
			helper.setSubject(subject);
			helper.setText(body, true);
			mailSender.send(message);
		}catch(Exception e) {
			logger.error("EmailUtil: sendEmail() Email sendnding failed");
			System.out.println(e.getMessage());
		}//try
		logger.debug("EmailUtil: sendEmail() ended");
		logger.debug("EmailUtil: sendEmail Executed Email Mailed");
	}//sendmail
}//EmailUtil