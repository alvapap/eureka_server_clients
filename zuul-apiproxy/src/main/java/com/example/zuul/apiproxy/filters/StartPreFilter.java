package com.example.zuul.apiproxy.filters;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import static com.netflix.zuul.context.RequestContext.getCurrentContext;

import java.time.Instant;

public class StartPreFilter extends ZuulFilter {

	@Override
	public Object run() {
		RequestContext ctx = getCurrentContext();
		ctx.set("startTime", Instant.now());
		return null;
	}

	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public int filterOrder() {
		return 1;
	}

	@Override
	public String filterType() {
		return "pre";
	}

}
