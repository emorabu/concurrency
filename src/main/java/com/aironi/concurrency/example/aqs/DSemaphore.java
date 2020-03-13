package com.aironi.concurrency.example.aqs;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * 获取多个许可
 * @author emora
 *
 */
public class DSemaphore {
	private final static int threadCount = 200;
	public static void main(String[] args) throws InterruptedException {
		ExecutorService threadPool = Executors.newCachedThreadPool();
		final Semaphore semaphore = new Semaphore(5); // 指定并发数
		for(int i=0;i<threadCount;i++) {
			final int num = i;
			
			threadPool.execute(()->{
				try {
					semaphore.acquire(2); // 获取多个许可
					test(num);
					semaphore.release(2); // 释放多个许可
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			});
		}
		threadPool.shutdown();
	}
	
	static void test(int i) throws InterruptedException {
		Thread.sleep(1000);
		System.out.println("thread num: "+i); // 输出是一块一块输出的(并发)
	}
}
