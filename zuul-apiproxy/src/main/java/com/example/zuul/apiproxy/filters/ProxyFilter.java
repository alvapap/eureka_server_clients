package com.example.zuul.apiproxy.filters;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import static com.netflix.zuul.context.RequestContext.getCurrentContext;

public class ProxyFilter extends ZuulFilter {

	@Override
	public Object run() {
		System.out.println("Filter called");
		return null;
	}

	@Override
	public boolean shouldFilter() {
		boolean isMobile = false;

		RequestContext ctx = getCurrentContext();
		String param = ctx.getRequest().getParameter("source");
		if (param != null && param.equals("mobile")) {
			isMobile = true;
		}

		return isMobile;
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
