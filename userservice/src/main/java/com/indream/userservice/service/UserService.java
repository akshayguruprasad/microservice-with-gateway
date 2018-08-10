package com.indream.userservice.service;

import com.indream.userservice.model.UserDto;
import com.indream.userservice.model.UserEntity;

public interface UserService {

	void registerUser(UserDto user);

	void activateUser(String token);

	String loginUser(UserDto user);

	void resetUserPassword(String id);

	void updatePassword(String token, UserDto user);

	void deleteUser(String token) ;
	UserEntity getUser(String user);

}
