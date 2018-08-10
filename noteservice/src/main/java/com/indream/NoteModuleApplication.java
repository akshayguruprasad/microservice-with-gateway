package com.indream;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class NoteModuleApplication {

	public static void main(String[] args) {
		SpringApplication.run(NoteModuleApplication.class, args);
	}
}
