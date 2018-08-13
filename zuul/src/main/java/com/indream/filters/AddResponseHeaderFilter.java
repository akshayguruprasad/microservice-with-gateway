package com.indream.filters;

import java.util.Map;

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
		String userTokenValue = RequestContext.getCurrentContext().getZuulRequestHeaders().get("authorization");
		Map<?, ?> result = (userTokenValue == null) ? null : redisOperation.checkToken(userTokenValue);
		if (result != null) {
			String userId = (String) result.get("userId");
			RequestContext.getCurrentContext().addZuulRequestHeader("userId", userId);
		}
		return null;
	}

}
