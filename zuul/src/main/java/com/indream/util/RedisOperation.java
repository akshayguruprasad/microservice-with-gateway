package com.indream.util;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import com.netflix.zuul.context.RequestContext;

import io.jsonwebtoken.Claims;

@Component
public class RedisOperation implements InitializingBean {

	private static final String COMMON_KEY = "TOKEN";
	/**
	 * TOKEN MANGER IMPLEMENTATION AUTOWIRE
	 */
	@Autowired
	private TokenManager manager;
	private BoundHashOperations<String, String, Token> boundHashOperations;
	/**
	 * REDIS TEMPLATE AUTOWIRING
	 */
	@Autowired
	RedisTemplate<String, Token> redisTemplate;

	public Map<?, ?> checkToken(String userLoginToken) {

		String token = RequestContext.getCurrentContext().getZuulRequestHeaders().get("authorization");
		Token valueToken = null;
		if (!boundHashOperations.hasKey(token)) {// NEW ENTRY FOUND
			Claims claims = manager.validateToken(token);// PARSE THE TOKEN
			valueToken = new Token();// CREATE TOKEN OBJ
			// SETTING ALL THE REQUIRED VALUES
			valueToken.setName(claims.get("name").toString());
			valueToken.setId(claims.get("id").toString());
			valueToken.setIssuedAt(claims.getIssuedAt());
			valueToken.setIssuer(claims.getIssuer());
			// STORE K V IN REDIS
			boundHashOperations.put(token, valueToken);
		}
		valueToken = boundHashOperations.get(token);
		Map<String, String> map = new HashMap<>();
		map.put("userId", valueToken.getId());
		return map;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		boundHashOperations = redisTemplate.boundHashOps(COMMON_KEY);

	}

}
