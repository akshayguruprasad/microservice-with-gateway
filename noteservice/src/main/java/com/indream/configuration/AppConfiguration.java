package com.indream.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.indream.util.MessageService;
import com.indream.util.SpringMailingServiceImpl;
import com.indream.util.TokenManagerImpl;

/**
 * APPLICATION CONFIGURATION SPRING IOC
 * 
 * @author Akshay
 *
 */
@Configuration
@PropertySource(value = { "classpath:application.properties", "classpath:ErrorProperties.properties",
		"classpath:LiteralProperties.properties", "classpath:mail.properties", "classpath:credentials.properties" })

public class AppConfiguration {

	/*
	 * @purpose TOKEN MANAGER SINGLETON BEAN CREATED TO USE
	 *
	 * @author akshay
	 * 
	 * @com.indream.fundoo.configuration
	 * 
	 * @since Jul 24, 2018
	 *
	 */
	@Bean(name = "manager")
	public TokenManagerImpl getTokenManagerImpl() {
		return new TokenManagerImpl();// NEW OPERATOR
	}

	/*
	 * @purpose PASSWORDENCODER BEAN CREATED TO ENCODE THE PASSWORD SPRING SECURITY
	 * CRYPTO
	 *
	 * @author akshay
	 * 
	 * @com.indream.fundoo.configuration
	 * 
	 * @since Jul 24, 2018
	 *
	 */
	@Bean(name = "passwordEncoder")
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();// NEW OPERATOR
	}

	/*
	 * @purpose SPRING IMPLEMENTATION FOR THE JAVA MAIL BEAN CREATED TO USE THE JAVA
	 * MAILING SERVICES
	 *
	 * @author akshay
	 * 
	 * @com.indream.fundoo.configuration
	 * 
	 * @since Jul 24, 2018
	 *
	 */
	@Bean(name = "springMessage")
	public MessageService getSpringBean() {

		return new SpringMailingServiceImpl();// NEW OPERATOR
	}

	/*
	 * @purpose JACKSON MAPPER BEAN CREATED TO BE AUTOWIRED TO MAP OBJECTS
	 *
	 * @author akshay
	 * 
	 * @com.indream.fundoo.configuration
	 * 
	 * @since Jul 24, 2018
	 *
	 */
	@Bean(name = "jacksonMapper")
	public ObjectMapper getObjectMapper() {
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		return mapper;

	}

	/*
	 * @purpose MODEL MAPPER BEAN CREATED FOR MAPPING PROPERTIES FROM ENTITY TO DTO
	 * v.v
	 *
	 * @author akshay
	 * 
	 * @com.indream.fundoo.configuration
	 * 
	 * @since Jul 24, 2018
	 *
	 */
	@Bean(name = "mapper")
	public ModelMapper getModelMapper() {
		return new ModelMapper();// NEW OPERATOR

	}

//	/*
//	 * @purpose APPLICATION SUPPORTS THE INTERCEPTORS AND ADDING TO REGISTERY
//	 *
//	 * @author akshay
//	 * 
//	 * @com.indream.fundoo.configuration
//	 * 
//	 * @since Jul 24, 2018
//	 *
//	 */
//	@Override
//	public void addInterceptors(InterceptorRegistry interceptorRegistry) {
//		interceptorRegistry.addInterceptor(getTokenInterceptor()).addPathPatterns("/noteapplication/**")
//				.addPathPatterns("/userapplication/update/password").addPathPatterns("/userapplication/update/password")
//				.addPathPatterns("/userapplication/delete/user").addPathPatterns("/userapplication/activate/account");
//
//	}

//	@Bean
//	public NoteService noteService() {
//
//		return new NoteServiceImpl();
//
//	}
//
//	/*
//	 * @purpose CREATION OF INTERCEPTOR BEAN
//	 *
//	 * @author akshay
//	 * 
//	 * @com.indream.fundoo.configuration
//	 * 
//	 * @since Jul 24, 2018
//	 *
//	 */
//	@Bean
//	public TokenValidatorInterceptor getTokenInterceptor() {
//		return new TokenValidatorInterceptor();// NEW OPERATOR
//	}

	@Bean
	public RestTemplate getRestTemplate() {

		RestTemplate restTemplate = new RestTemplate();

		return restTemplate;

	}


}// AppConfiguration class ends

