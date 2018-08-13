package com.indream.util;

import io.jsonwebtoken.Claims;

/**
 * TOKEN SERVICE METHODS
 * 
 * @author Akshay
 *
 */
public interface TokenManager {



	Claims validateToken(String token);

}
