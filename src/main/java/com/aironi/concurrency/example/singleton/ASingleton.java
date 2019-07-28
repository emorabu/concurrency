package com.aironi.concurrency.example.singleton;

import com.aironi.concurrency.annotations.ThreadUnsafe;

/**
 * 懒汉模式
 * @author emora
 *
 */
@ThreadUnsafe
public class ASingleton {
	private ASingleton() {}
	
	private static ASingleton instance = null;
	
	public static ASingleton getInstance() {
		if(instance==null) { // 判断和初始化中间可能会被打断
			instance = new ASingleton();
		}
		return instance;
	}
}
