package com.aironi.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class ABeanTest {
	public static void main(String[] args) {
//		FileSystemXmlApplicationContext applicationContext = new FileSystemXmlApplicationContext("c:/xxx.xml");
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:xxx.xml");
//		applicationContext.getBean(requiredType)
		a();
	}
	static void a(int... b) {
		for (int i : b) {
			
		}
	}
}
