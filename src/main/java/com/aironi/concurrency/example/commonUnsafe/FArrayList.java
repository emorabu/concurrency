package com.aironi.concurrency.example.commonUnsafe;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * ArrayList 线程不安全
 * @author emora
 *
 */
public class FArrayList {
	public static int clientTotal = 5000; // 请求总数
	public static int threadTotal = 200; // 并发线程数
	private static final Logger LOGGER = LoggerFactory.getLogger(FArrayList.class);
	
	private static List<Integer> list = new ArrayList<>();
	
	public static void main(String[] args) throws Exception {
		ExecutorService threadPool = Executors.newCachedThreadPool(); // 创建线程池
		final Semaphore semaphore = new Semaphore(threadTotal); // 定义信号量
		final CountDownLatch countDownLatch = new CountDownLatch(clientTotal); // 计数器闭锁
		for (int i = 0; i < clientTotal; i++) {
			final int count = i;
			threadPool.execute(() -> {
				try {
					semaphore.acquire();
					update(count);
					semaphore.release();
				}catch (Exception e) {
					LOGGER.debug(e.getMessage());
				}
				countDownLatch.countDown();
			});
		}
		countDownLatch.await();
		threadPool.shutdown();
		LOGGER.info("{}",list.size()); // 每次运行结果不同, 总是小于预期值 5000
	}

	private static void update(int count)  {
		list.add(count);
	}
}
