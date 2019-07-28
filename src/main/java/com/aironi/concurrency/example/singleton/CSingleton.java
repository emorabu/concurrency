package com.aironi.concurrency.example.singleton;

import com.aironi.concurrency.annotations.NotRecommend;
import com.aironi.concurrency.annotations.ThreadSafe;

/**
 * 懒汉模式: 使用 synchronized, 性能差
 * @author emora
 *
 */
@ThreadSafe
@NotRecommend
public class CSingleton {
	private CSingleton() {}
	
	private static CSingleton instance = null;
	
	public static synchronized CSingleton getInstance() {
		if(instance==null) { 
			instance = new CSingleton();
		}
		return instance;
	}
}
