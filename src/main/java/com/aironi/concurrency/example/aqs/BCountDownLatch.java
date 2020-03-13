package com.aironi.concurrency.example.aqs;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 指定时间内完成, 如果到了时间还没执行完, 就不关心了
 * @author emora
 *
 */
public class BCountDownLatch {
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
		latch.await(101,TimeUnit.MILLISECONDS); // 指定等待时间, 到了这个时间就继续执行下面的操作, 而不必等到计数器为0
		// 比如 countDown() 没在 finally 中而抛出了异常, 会导致程序一直等待.
		System.out.println("finish..."); // finish 最后输出
		threadPool.shutdown();
	}
	
	static void test(int i) throws InterruptedException {
		Thread.sleep(100);
		System.out.println("thread num: "+i);
	}
}
