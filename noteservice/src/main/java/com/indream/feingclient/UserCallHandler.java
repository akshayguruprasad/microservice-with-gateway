package com.indream.feingclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.indream.noteservice.model.UserEntity;

@FeignClient(name = "userservice")
public interface UserCallHandler {
	@RequestMapping(path = "/userapplication/user", method = RequestMethod.GET)
	UserEntity findUserEntityById(@RequestHeader("user")  String userId);

	@GetMapping(path = "/userapplication/msg")
	String getMessage();
}
