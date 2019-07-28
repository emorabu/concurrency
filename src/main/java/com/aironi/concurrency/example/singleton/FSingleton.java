package com.aironi.concurrency.example.singleton;

import com.aironi.concurrency.annotations.ThreadSafe;

/**
 * 饿汉模式, 线程安全
 * @author emora
 *
 */
@ThreadSafe
public class FSingleton {
	private FSingleton() {
	}

	private static FSingleton instance = null; // 这行声明一定要写在 static 块前, 否则初始化后又变成了 null
	static {
		instance = new FSingleton();
	}

	public static FSingleton getInstance() {
		return instance;
	}

}
