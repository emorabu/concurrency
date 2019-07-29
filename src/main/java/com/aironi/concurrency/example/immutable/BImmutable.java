package com.aironi.concurrency.example.immutable;

import java.util.Collections;
import java.util.Map;

import com.aironi.concurrency.annotations.ThreadSafe;
import com.google.common.collect.Maps;

@ThreadSafe
public class BImmutable {
	private  static Map<Integer, Integer> map = Maps.newHashMap();
	static {
		map.put(1, 2);
		map.put(3, 4);
		map.put(5, 6);
		map = Collections.unmodifiableMap(map); // 将原 map 内容放入新的 UnmodifiableMap<>(m) 中, 所有数据修改操作都会抛出异常
	}
	public static void main(String[] args) {
		map.put(1, 5); // 抛出异常 java.lang.UnsupportedOperationException, 不能修改内容
		
	}
}
