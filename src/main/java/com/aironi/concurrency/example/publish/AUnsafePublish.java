package com.aironi.concurrency.example.publish;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aironi.concurrency.annotations.ThreadUnsafe;

@ThreadUnsafe
public class AUnsafePublish {
	private static final Logger LOGGER = LoggerFactory.getLogger(AUnsafePublish.class);
	private String[] states = {"a","b","c"};
	public String[] getStates() {
		return states;
	}
	public static void main(String[] args) {
		AUnsafePublish a = new AUnsafePublish();
		LOGGER.info(Arrays.toString(a.getStates())); // [a, b, c]
		a.getStates()[0]="1"; // 其他线程可以修改这个私有变量, 获取到的结果将是不确定的
		LOGGER.info(Arrays.toString(a.getStates())); // [1, b, c]
		
	}
}
