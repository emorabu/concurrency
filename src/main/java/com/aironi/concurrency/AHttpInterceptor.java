package com.aironi.concurrency;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.aironi.concurrency.example.threadLocal.RequestHolder;

public class AHttpInterceptor extends HandlerInterceptorAdapter{
	private static final Logger LOGGER = LoggerFactory.getLogger(AHttpInterceptor.class);
	
	/**
	 * 请求 Controller 方法前的预处理
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		LOGGER.info("preHandle");
		return true;
	}
	
	/**
	 * 请求完 Controller 方法但没返回给客户端的处理
	 */
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
		LOGGER.info("afterCompletion");
		RequestHolder.remove(); // 防止内存泄漏
	}
}
