package com.indream.util;

import javax.mail.MessagingException;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.scheduling.annotation.Async;

/** SPRING MAILING SERVICE TO SEND THE MAIL
 * @author Akshay
 *
 */
public class SpringMailingServiceImpl implements MessageService {

	@Autowired
	private Environment env;

	@Autowired
	private JavaMailSenderImpl springMail;

	/*
     * @purpose SEND THE MAIL TO THE APPROPRIATE USER MAKES A NEW THREAD FOR THE
     * CALLER OF THIS METHOD PROVIDES A ASYNC BEHAVIOUR TO THE APPLICATION
     * 
     * @author akshay
     * 
     * @com.indream.fundoo.util
     * 
     * @since Jul 24, 2018
     *
     */
	@Override
	public void sendMessage(String userEmail, String subject, String message)
			throws IllegalStateException, MessagingException {
//		System.out.println("ABC12345six");
//		System.out.println("portal.name1@gmail.com");
		springMail.setPassword(/*env.getProperty("admin.password")*/"ABC12345six");//SET THE USER PASSWORD
		springMail.setUsername(/*env.getProperty("admin.email")*/"portal.name1@gmail.com");//SET THE USER NAME
		SimpleMailMessage messageSimple = new SimpleMailMessage();//CREATE A NEW MESSAGE
		messageSimple.setText(message);//SET THE TEXT 
		messageSimple.setTo(userEmail.trim());//RECIVER
		messageSimple.setSubject(subject);//SET THE SUBJECT
	springMail.send(messageSimple);// SEND THE MAIL
	}

}
