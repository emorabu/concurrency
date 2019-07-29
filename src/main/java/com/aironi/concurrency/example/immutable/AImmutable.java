package com.aironi.concurrency.example.immutable;

import java.util.Map;

import com.aironi.concurrency.annotations.ThreadUnsafe;
import com.google.common.collect.Maps;

@ThreadUnsafe
public class AImmutable {
	private final static Integer a =1;
	private final static String b = "2";
	
	// final 修改引用类型变量, 只是不能指向新的对象, 但其属性值可以修改
	private final static Map<Integer, Integer> map = Maps.newHashMap();
	static {
		map.put(1, 2);
		map.put(3, 4);
		map.put(5, 6);
	}
	public static void main(String[] args) {
		map.put(1, 5);
		System.out.println(map.get(1)); // 5
		
	}
	
	// final 也可以修饰方法形参
	
}
