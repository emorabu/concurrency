package com.aironi.concurrency.example.immutable;

import com.aironi.concurrency.annotations.ThreadSafe;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;

@ThreadSafe
public class CImmutable {
	private final static ImmutableList<Integer> list = ImmutableList.of(1, 2, 3);
	private final static ImmutableSet<Integer> set= ImmutableSet.copyOf(list);
	private final static ImmutableMap<Integer, Integer> map = ImmutableMap.of(1, 2, 3, 4); // <1,2> <3,4>
	private final static ImmutableMap<Integer, Integer> map2 = ImmutableMap.<Integer,Integer>builder().put(1, 2).put(3, 4).build();
	public static void main(String[] args) {
//		list.add(1); // 显示删除线, 抛出 java.lang.UnsupportedOperationException
		map.put(1, 2); // 显示删除线, 抛出 java.lang.UnsupportedOperationException
	}
}
