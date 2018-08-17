package com.indream.util;

import org.apache.log4j.Logger;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.indream.userservice.model.MailEntity;

/**
 * MAIL LISTENER FOR THE CONSUMER TO READ MESSAGE FROM THE QUEUE
 * 
 * @author Akshay
 *
 */
@Component
public class MailListener {
    private static final Logger LOG = Logger.getLogger(MailListener.class);
    @Autowired
    MessageService springMessage;

    /*
     * @purpose SEND THE MAIL TO THE REVICER
     *
     * @author akshay
     * 
     * @com.indream.fundoo.util
     * 
     * @since Jul 24, 2018
     *
     */
    @RabbitListener(queues= {"EmailBuffer"})
    public void sendEmail(String message) {
	MailEntity mail = Utility.convertFromJSONString(message, MailEntity.class);

	try {
	    springMessage.sendMessage(mail.getTo(), mail.getSubject(), mail.getMessage());
	} catch (Exception e) {
	    LOG.error("Exception in [maillistener] [sendmail] " + e.getMessage());
	}

    }



}
