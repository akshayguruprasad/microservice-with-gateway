package com.indream;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class SampleApplication {

	@Value("${message}")
	String name;

	public static void main(String[] args) {
		SpringApplication.run(SampleApplication.class, args);
	}

	@GetMapping(path = "/msg")
	public String message() {
		System.out.println(name);

		return name;

	}

}
