package com.indream.configuration;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiKey;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * CONFIGURATION OF THE SWAGGER
 * 
 * @author Akshay
 *
 */
@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

    /*
     * @purpose CREATION OF THE DOCKET BEAN FOR READING THE RESTCONTROLLER
     * IMPLEMENTAIONS
     *
     * @author akshay
     * 
     * @com.indream.fundoo.configuration
     * 
     * @since Jul 24, 2018
     *
     */
    @Bean
    public Docket api() {
	return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.any()).paths(PathSelectors.any()).build()
			.securitySchemes(Collections.singletonList(apiKey()));
    }

    /*
     * @purpose API KEY VALUE PROVIDED FOR THE SWAGGER UI - MANAGEMENT
     *
     * @author akshay
     * 
     * @com.indream.fundoo.configuration
     * 
     * @since Jul 24, 2018
     *
     */
    private ApiKey apiKey() {
	return new ApiKey("authorization", "authorization", "header");
    }

  
}
