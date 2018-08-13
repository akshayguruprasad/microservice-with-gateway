package com.indream.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

import com.indream.util.Token;

import redis.clients.jedis.JedisPoolConfig;

@Configuration
public class RedisConfig {

	/*
	 * @purpose REDIS CLI CONNECTION FACTORY BEAN CREATION
	 *
	 * @author akshay
	 * 
	 * @com.indream.fundoo.configuration
	 * 
	 * @since Jul 24, 2018
	 *
	 */
	@Bean
	JedisConnectionFactory jedisConnectionFactory() {
		JedisConnectionFactory x = new JedisConnectionFactory();
		x.setPort(6379);// DEFAULT PORT FOR THE REDIS
		x.setHostName("localhost");// USING LOCALHOST FOR THE REDIS SERVER TO RUN ON
		x.setUsePool(true);// USING OF REDIS CONNECTION POOL
		x.setPoolConfig(getPoolConnection());// CONFIGURATION FOR THE REDIS
		return x;
	}

	/*
	 * @purpose CREATION OF THE TEMPLATE TO AVOID BOILER PLATE CODES
	 *
	 * @author akshay
	 * 
	 * @com.indream.fundoo.configuration
	 * 
	 * @since Jul 24, 2018
	 *
	 */
	@Bean(name = "redisTemplate")
	public RedisTemplate redisTemplate() {
		RedisTemplate<String, Token> template = new RedisTemplate<String, Token>();
		template.setConnectionFactory(jedisConnectionFactory());
		return template;
	}

	/*
	 * @purpose
	 * 
	 *
	 * @author akshay
	 * 
	 * @com.indream.fundoo.configuration
	 * 
	 * @since Jul 24, 2018
	 *
	 */
	public JedisPoolConfig getPoolConnection() {
		return new JedisPoolConfig();// CONFIGURATION DEFAULT

	}

	@Bean
	public PropertySourcesPlaceholderConfigurer getSourceProperties() {

		PropertySourcesPlaceholderConfigurer placeholderConfigurer = new PropertySourcesPlaceholderConfigurer();
		placeholderConfigurer.setLocation((new ClassPathResource("message.properties")));
		return placeholderConfigurer;
	}

}
