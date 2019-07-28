package com.aironi.concurrency.example.singleton;

import com.aironi.concurrency.annotations.ThreadSafe;

/**
 * 懒汉模式: 使用 synchronized, 性能差
 * @author emora
 *
 */
@ThreadSafe
public class ESingleton {
	private ESingleton() {
	}

	/**
	 * 使用 volatile 防止指令重排序, 这样初始化就不会有线程安全问题了
	 * volatile + 双重检测机制 可以防止指令重排引起的线程安全问题
	 */
	private static volatile ESingleton instance = null;

	public static ESingleton getInstance() {
		if (instance == null) { // 双重检测机制 // 
			synchronized (ESingleton.class) { // 同步锁				
				if (instance == null) {
					instance = new ESingleton();
				}
			}
		}
		return instance;
	}
}
