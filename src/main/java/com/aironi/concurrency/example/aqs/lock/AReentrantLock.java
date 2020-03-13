package com.aironi.concurrency.example.aqs.lock;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class AReentrantLock {
	public static int clientTotal = 5000; // 请求总数
	public static int threadTotal = 200; // 并发线程数
	private static int count = 0;
	private final static Lock lock = new ReentrantLock();
	
	public static void main(String[] args) throws InterruptedException {
		ExecutorService threadPool = Executors.newCachedThreadPool();
		final Semaphore semaphore = new Semaphore(threadTotal);
		final CountDownLatch latch = new CountDownLatch(clientTotal);
		for(int i=0;i<clientTotal;i++) {
			threadPool.execute(()->{
				try {
					semaphore.acquire();
					add();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}finally {
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
		try {
			lock.lock();
			count++;
		} finally {
			lock.unlock();
		}
	}
}
