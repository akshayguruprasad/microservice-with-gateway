package com.indream.userservice.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.indream.configuration.I18NSpec;
import com.indream.userservice.model.UserDto;
import com.indream.userservice.model.UserEntity;
import com.indream.userservice.model.UserResponse;
import com.indream.userservice.service.UserService;
import com.indream.util.Utility;

@RestController
@RequestMapping("/userapplication")
@PropertySource("classpath:application-deployment.properties")
public class UserController {
	@Autowired
	private UserService service;
//	@Value("${name}")
//	private String name;
	@Autowired
	private I18NSpec i18N;

	@Autowired
	private Environment env;

	final Logger LOG = Logger.getLogger(UserController.class);

	@RequestMapping(path = "/registeration", method = RequestMethod.POST)
	public ResponseEntity<UserResponse> userRegisteration(@RequestBody UserDto userDto) {
		service.registerUser(userDto);
		return new ResponseEntity<UserResponse>(new UserResponse(i18N.getMessage("user.create.success"), 1),
				HttpStatus.OK);
	}

	@RequestMapping(path = "/activate/account", method = RequestMethod.GET)
	public ResponseEntity<UserResponse> activateAccount(HttpServletRequest request) {
		String token = (String) request.getSession().getAttribute("token");
		service.activateUser(token);
		return new ResponseEntity<UserResponse>(new UserResponse(i18N.getMessage("user.activate.success"), 2),
				HttpStatus.OK);

	}

	@RequestMapping(path = "/login", method = RequestMethod.POST)
	public ResponseEntity<UserResponse> userLogin(@RequestBody UserDto user) {
		String tokenData = service.loginUser(user);
		return new ResponseEntity<UserResponse>(new UserResponse(tokenData, 3), HttpStatus.OK);
	}

	@RequestMapping(path = "/reset/password/{email:.+}", method = RequestMethod.PUT)
	public ResponseEntity<UserResponse> forgotPassword(@PathVariable("email") String email) {
		service.resetUserPassword(email);
		return new ResponseEntity<UserResponse>(new UserResponse(i18N.getMessage("user.password.reset.success"), 4),
				HttpStatus.OK);

	}

	@RequestMapping(path = "/update/password", method = RequestMethod.PUT)

	public ResponseEntity<UserResponse> updatePassword(@RequestBody UserDto userDto, HttpServletRequest request) {
		String token = (String) request.getSession().getAttribute("token");
		service.updatePassword(token, userDto);
		return new ResponseEntity<UserResponse>(new UserResponse(i18N.getMessage("user.password.update.success"), 5),
				HttpStatus.OK);
	}

	@RequestMapping(path = "/delete/user", method = RequestMethod.DELETE)

	public ResponseEntity<UserResponse> deleteUser(HttpServletRequest request) {

		String token = (String) request.getSession().getAttribute("token");

		service.deleteUser(token);
		return new ResponseEntity<UserResponse>(new UserResponse(i18N.getMessage("user.delete.success"), 6),
				HttpStatus.OK);
	}

	@GetMapping("/user/{userId}")
	public ResponseEntity<UserResponse> getUser(HttpServletRequest request, @PathVariable("userId") String userId) {
		UserEntity user = service.getUser(userId);
		String message = Utility.covertToJSONString(user);
		return new ResponseEntity<UserResponse>(new UserResponse(message, 7), HttpStatus.OK);
	}

	@GetMapping(path = "/msg")
	public String message(HttpServletRequest request) {
		String data = request.getHeader("name");
		return data;

	}

}
