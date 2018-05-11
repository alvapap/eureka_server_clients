package com.example.zuul.apiproxy.filters;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import static com.netflix.zuul.context.RequestContext.getCurrentContext;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

public class StopPostFilter extends ZuulFilter {

	@Override
	public Object run() {
		
		Instant stop = Instant.now();
		RequestContext ctx = getCurrentContext();
		Instant start = (Instant)ctx.get("startTime"); 
		long milliDifference = ChronoUnit.MILLIS.between(start, stop);
		System.out.println("Call took " + milliDifference + " milliseconds");
		
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
		return "post";
	}

}
