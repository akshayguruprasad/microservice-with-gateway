package com.indream;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

//	@Value("${name}")
	private String name;

	@GetMapping("/home")
	public String name() {
		System.out.println(name);
		return name;
	}

}
