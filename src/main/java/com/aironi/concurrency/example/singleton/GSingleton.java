package com.aironi.concurrency.example.singleton;

import com.aironi.concurrency.annotations.Recommend;
import com.aironi.concurrency.annotations.ThreadSafe;

/**
 * 使用枚举, 线程安全
 * @author emora
 *
 */
@ThreadSafe
@Recommend
public class GSingleton {
	private GSingleton() {
	}

	public static GSingleton getInstance() {
		return Singleton.INSTANCE.getInstance();
	}
	private enum Singleton{
		INSTANCE;
		private GSingleton outer;
		
		// JVM 保证这个方法只调用一次
		private Singleton() {
			outer = new GSingleton();
		}
		public GSingleton getInstance() {
			return outer;
		}
	}

}
