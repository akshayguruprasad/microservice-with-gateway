package com.indream.filters;

import java.util.Enumeration;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import com.indream.util.RedisOperation;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

public class AddResponseHeaderFilter extends ZuulFilter {

	@Autowired
	HttpServletRequest request;

	@Autowired
	RedisOperation redisOperation;

	@Override
	public String filterType() {

		return "pre";
	}

	@Override
	public int filterOrder() {

		return 1;
	}

	@Override
	public boolean shouldFilter() {

		return true;
	}

	@Override
	public Object run() throws ZuulException {
		return callFilter();

	}

	private Object callFilter() {
		System.out.println("Enters the callfilter ");

		String userTokenValue =		RequestContext.getCurrentContext().getRequest().getHeader("authorization");
	
		System.out.println(userTokenValue);

		
		Map<?, ?> result=null;
		if ((result=redisOperation.checkToken(userTokenValue)) != null) {
			String userId = (String) result.get("userId");
			System.out.println("Current value for the userId " + userId);
			RequestContext.getCurrentContext().addZuulRequestHeader("userId", userId);
		}
		return null;
	}

}
