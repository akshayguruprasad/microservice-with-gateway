package com.indream.configuration;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;

import com.indream.util.MailListener;

/**
 * RABBITMQ CONFIGURATION
 * 
 * @author Akshay
 *
 */
@Configuration
public class RabbitMqConfig {
	public static final String TOPICEXCHANGENAME = "DirectMessageExchange";

	static final String QUEUENAME = "EmailBuffer";
	public static final String ROUTING_KEY = "EmailKey";
	static final String LISTENERMETHOD = "sendEmail";

	/*
	 * @purpose BEAN FOR THE QUEUE
	 *
	 * @author akshay
	 * 
	 * @com.indream.fundoo.configuration
	 * 
	 * @since Jul 24, 2018
	 *
	 */
	@Bean
	public Queue queue() {
		return new Queue(QUEUENAME, false);
	}

	/*
	 * @purpose BEAN FOR THE EXCHANGE
	 *
	 * @author akshay
	 * 
	 * @com.indream.fundoo.configuration
	 * 
	 * @since Jul 24, 2018
	 *
	 */
	@Bean
	public TopicExchange exchange() {
		return new TopicExchange(TOPICEXCHANGENAME);
	}

	/*
	 * @purpose BINDING OBJECT BEAN
	 *
	 * @author akshay
	 * 
	 * @com.indream.fundoo.configuration
	 * 
	 * @since Jul 24, 2018
	 *
	 */
	@Bean
	public Binding binding(Queue queue, TopicExchange exchange) {
		return BindingBuilder.bind(queue).to(exchange).with(ROUTING_KEY);
	}

	/*
	 * @purpose SIMPLELISTENER FOR CREATION AND MAINTAINANCE OF THE LISTENER THREADS
	 *
	 * @author akshay
	 * 
	 * @com.indream.fundoo.configuration
	 * 
	 * @since Jul 24, 2018
	 *
	 */
	/*@Bean
	public SimpleMessageListenerContainer container(ConnectionFactory connectionFactory) {
		SimpleMessageListenerContainer container = null;
		try {
			container = new SimpleMessageListenerContainer(connectionFactory);
			container.setQueueNames(QUEUENAME);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return container;

	}*/

	/*
	 * @purpose RABBITMQ TEMPLATE TO AVOID CONFIGURATIONS
	 *
	 * @author akshay
	 * 
	 * @com.indream.fundoo.configuration
	 * 
	 * @since Jul 24, 2018
	 *
	 */
	@Bean(name = "template")
	public AmqpTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
		final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
		return rabbitTemplate;
	}
}
