package com.aironi.concurrency.example.aqs.lock;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.StampedLock;

/**
 * 写, 读, 乐观读
 * 版本和模式组成
 * 获取锁返回数字, 作为 stamped, 用来做为锁状态控制相关访问, 数字0表示没有写锁锁定访问, 
 * 读锁分为悲观锁和乐观锁(读多写少, 可提高程序吞吐量)
 * @author emora
 *
 */
public class CStampedLock {
	public static int clientTotal = 5000; // 请求总数
	public static int threadTotal = 200; // 并发线程数
	private static int count = 0;
	private final static StampedLock lock = new StampedLock();

	public static void main(String[] args) throws InterruptedException {
		ExecutorService threadPool = Executors.newCachedThreadPool();
		final Semaphore semaphore = new Semaphore(threadTotal);
		final CountDownLatch latch = new CountDownLatch(clientTotal);
		for (int i = 0; i < clientTotal; i++) {
			threadPool.execute(() -> {
				try {
					semaphore.acquire();
					add();
				} catch (InterruptedException e) {
					e.printStackTrace();
				} finally {
					semaphore.release();
					latch.countDown();
				}
			});
		}
		latch.await();
		System.out.println(count); // 总是 5000
		threadPool.shutdown();
	}

	static void add() {
		long stamp = 0;
		try {
			stamp = lock.writeLock();
			count++;
		} finally {
			lock.unlock(stamp);
		}
	}
}
