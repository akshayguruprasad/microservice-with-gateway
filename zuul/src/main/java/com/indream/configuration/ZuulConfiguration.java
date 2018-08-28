package com.indream.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.indream.filters.AddResponseHeaderFilter;

@Configuration
public class ZuulConfiguration {

	@Bean
	public AddResponseHeaderFilter addResponseHeaderFilter() {
		return new AddResponseHeaderFilter();
	}

}
