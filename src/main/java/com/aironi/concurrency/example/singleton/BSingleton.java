package com.aironi.concurrency.example.singleton;

import com.aironi.concurrency.annotations.ThreadSafe;

/**
 * 饿汉模式, 线程安全
 * @author emora
 *
 */
@ThreadSafe
public class BSingleton {
	private BSingleton() { // 如果构造方法时有很多处理, 但该类可能没有被使用, 造成资源浪费
	}

	private static BSingleton instance = new BSingleton();

	public static BSingleton getInstance() {
		return instance;
	}

}
