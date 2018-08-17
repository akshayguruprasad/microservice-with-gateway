package com.indream.filters;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import com.indream.util.RedisOperation;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

public class AddRequestFilter extends ZuulFilter {

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
		String uri = RequestContext.getCurrentContext().getRequest().getRequestURI();
		if (uri.startsWith("/userservice/userapplication/login")
				|| uri.startsWith("/userservice/userapplication/registeration")
				|| uri.startsWith("/userservice/userapplication/reset/password/")) {

			return null;

		}

		String userTokenValue = RequestContext.getCurrentContext().getRequest().getHeader("authorization");
		System.out.println(userTokenValue + " -- value ");
		Map<?, ?> result = null;
		if ((result = redisOperation.checkToken(userTokenValue)) != null) {
			String userId = (String) result.get("userId");
			System.out.println("UserId " + userId);
			RequestContext.getCurrentContext().addZuulRequestHeader("userId", userId);
		}
		System.out.println("Returning the value ");
		return null;
	}

}
