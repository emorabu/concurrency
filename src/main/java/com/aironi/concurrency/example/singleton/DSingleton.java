package com.aironi.concurrency.example.singleton;

import com.aironi.concurrency.annotations.NotRecommend;
import com.aironi.concurrency.annotations.ThreadUnsafe;

/**
 * 懒汉模式: 使用 synchronized, 性能差
 * @author emora
 *
 */
@ThreadUnsafe
@NotRecommend
public class DSingleton {
	private DSingleton() {
	}

	private static DSingleton instance = null;

	/**
	 * 线程不安全
	 * 	执行 instance = new DSingleton(); 需要三步操作:
	 * 		1. memory = allocate() 分配对象内存空间
	 * 		2. ctorInstance() 初始化对象
	 * 		3. instance = memory 设置 instance 指向刚分配的内存
	 * 	JVM 和 cpu 优化， 发生了指令重排序。
	 * 		以上顺序变为 1 3 2
	 * 
	 * 	A 线程持有锁, 执行到 instance = new DSingleton(); 对应上面的 3 操作, instance 指向了一块内存, 但没有内容
	 * 	B 线程执行到 if (instance == null) 将获取到一个没有被初始化的对象, 直接返回就会有问题.
	 * @return
	 */
	public static DSingleton getInstance() {
		if (instance == null) { // 双重检测机制 // 
			synchronized (DSingleton.class) { // 同步锁				
				if (instance == null) {
					instance = new DSingleton();
				}
			}
		}
		return instance;
	}
}
