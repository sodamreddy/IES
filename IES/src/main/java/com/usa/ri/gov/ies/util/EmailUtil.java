package com.usa.ri.gov.ies.util;
import javax.mail.internet.MimeMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
/**
 * this class is used for sending mail to Applicant on Account Creation
 * @author sodam
 *
 */
@Component
public class EmailUtil {
	Logger logger = LoggerFactory.getLogger(EmailUtil.class);
	@Autowired(required = true)
	private JavaMailSender mailSender;
	
	/**this method is used for setting the format of mail 
	 * 		on successful registration 
	 * @param to
	 * @param subject
	 * @param text
	 */

	public void sendMail(String to, String subject, String text) {
		logger.debug("EmailUtil: sendMail()  Starated");
		try {
			MimeMessage message = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
			;
			helper.setTo(to);
			helper.setSubject(subject);
			helper.setText(text, true);
			mailSender.send(message);
			logger.debug("EmailUtil: sendMail()  Ended");
			logger.info(" EmailUtil: sendMail() Executed");
		} catch (Exception e) {
			logger.warn("Email sending failed " + e.getMessage());
		} // try
	}

}
