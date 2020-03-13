package com.aironi.concurrency.example.aqs;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ACountDownLatch {
	private final static int threadCount = 200;
	public static void main(String[] args) throws InterruptedException {
		ExecutorService threadPool = Executors.newCachedThreadPool();
		final CountDownLatch latch = new CountDownLatch(threadCount);
		for(int i=0;i<threadCount;i++) {
			final int num = i;
			threadPool.execute(()->{
				try {
					test(num);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}finally {
					latch.countDown();
				}
			});
		}
		latch.await();
		System.out.println("finish..."); // finish 最后输出
		threadPool.shutdown();
	}
	
	static void test(int i) throws InterruptedException {
		Thread.sleep(1000);
		System.out.println("thread num: "+i);
	}
}
