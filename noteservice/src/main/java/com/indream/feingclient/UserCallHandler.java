package com.indream.feingclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.indream.noteservice.model.UserEntity;

@FeignClient( value="userservice",url="http://localhost:9999")
public interface UserCallHandler {
	@RequestMapping(path = "/userapplication/user/{userId}", method = RequestMethod.GET)
	UserEntity findUserEntityById(@PathVariable(value="userId") String userId);

}
