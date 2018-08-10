package com.indream.filters;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

public class AddResponseHeaderFilter extends ZuulFilter {

	@Autowired
	HttpServletRequest request;

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
		RequestContext.getCurrentContext().addZuulRequestHeader("name", "Lion");
				return null;
	}

}
