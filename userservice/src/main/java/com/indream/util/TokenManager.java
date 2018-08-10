package com.indream.util;

import io.jsonwebtoken.Claims;
import com.indream.userservice.model.UserEntity;

/**
 * TOKEN SERVICE METHODS
 * 
 * @author Akshay
 *
 */
public interface TokenManager {

	String generateToken(UserEntity requester);

	Claims validateToken(String token);

}
