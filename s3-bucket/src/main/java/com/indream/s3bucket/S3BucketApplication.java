package com.indream.s3bucket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class S3BucketApplication {

	public static void main(String[] args) {
		SpringApplication.run(S3BucketApplication.class, args);
	}
}
