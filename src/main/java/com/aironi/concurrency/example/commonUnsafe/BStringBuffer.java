package com.aironi.concurrency.example.commonUnsafe;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * StringBuffer 线程安全
 * @author emora
 *
 */
public class BStringBuffer {
	public static int clientTotal = 5000; // 请求总数
	public static int threadTotal = 200; // 并发线程数
	public static StringBuffer sb = new StringBuffer();
	private static final Logger LOGGER = LoggerFactory.getLogger(BStringBuffer.class);

	public static void main(String[] args) throws Exception {
		ExecutorService threadPool = Executors.newCachedThreadPool(); // 创建线程池
		final Semaphore semaphore = new Semaphore(threadTotal); // 定义信号量
		final CountDownLatch countDownLatch = new CountDownLatch(clientTotal); // 计数器闭锁
		for (int i = 0; i < clientTotal; i++) {
			threadPool.execute(() -> {
				try {
					semaphore.acquire();
					update();
					semaphore.release();
					countDownLatch.countDown();
				} catch (Exception e) {
					e.printStackTrace();
				}
			});
		}
		countDownLatch.await();
		threadPool.shutdown();
		LOGGER.info("count:" + sb.length()); // 每次数值均为 5000
	}

	private static void update() {
		sb.append(1);
	}
}
