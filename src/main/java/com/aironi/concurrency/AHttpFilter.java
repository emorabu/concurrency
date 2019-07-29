package com.aironi.concurrency;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aironi.concurrency.example.threadLocal.RequestHolder;

public class AHttpFilter implements Filter {
	private static final Logger LOGGER = LoggerFactory.getLogger(AHttpFilter.class);
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		LOGGER.info("do filter, {}, {}", Thread.currentThread().getId(), req.getServletPath());
		
		RequestHolder.add(Thread.currentThread().getId());
		// 一定要放行
		chain.doFilter(request, response);
	}

}
