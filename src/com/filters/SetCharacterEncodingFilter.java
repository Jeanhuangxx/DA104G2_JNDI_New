package com.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class SetCharacterEncodingFilter implements Filter{
	
	protected String encoding = null;
//	protected String contype = null;
	protected FilterConfig config = null;
	
	@Override
	public void init(FilterConfig config) throws ServletException {
		this.config = config;
		this.encoding = config.getInitParameter("encoding");
//		this.contype = config.getInitParameter("contype");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {		
		request.setCharacterEncoding(encoding);
//		response.setContentType(contype);
		chain.doFilter(request, response);	
	}
	
	@Override
	public void destroy() {
		this.encoding = null;
		this.config = null;
//		this.contype = null;

	}

}
