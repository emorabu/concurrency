package com.aironi.concurrency.example.atomic;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aironi.concurrency.annotations.ThreadSafe;

@ThreadSafe
public class AtomicExample1 {
	public static int clientTotal = 5000; // 请求总数
	public static int threadTotal = 200; // 并发线程数
	public static AtomicInteger count = new AtomicInteger(0); // 计数器
	private static final Logger LOGGER = LoggerFactory.getLogger(AtomicExample1.class);

	public static void main(String[] args) throws Exception {
		ExecutorService threadPool = Executors.newCachedThreadPool(); // 创建线程池
		final Semaphore semaphore = new Semaphore(threadTotal); // 定义信号量
		final CountDownLatch countDownLatch = new CountDownLatch(clientTotal); // 计数器闭锁
		for (int i = 0; i < clientTotal; i++) {
			threadPool.execute(() -> {
				try {
					semaphore.acquire();
					add();
					semaphore.release();
				}catch (Exception e) {
					LOGGER.debug(e.getMessage());
				}
				countDownLatch.countDown();
			});
		}
		countDownLatch.await();
		threadPool.shutdown();
		LOGGER.info("count:" + count.get()); // 每次输出的值均为5000
	}

	private static void add() {
		count.incrementAndGet();
	}
}
