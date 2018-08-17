package com.indream.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.indream.filters.AddRequestFilter;

@Configuration
public class ZuulConfiguration {

	@Bean
	public AddRequestFilter addRequestFilter() {
		return new AddRequestFilter();
	}

}
