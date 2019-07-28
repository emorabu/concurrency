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
public class AtomicExample7 {
	public static int clientTotal = 5000; // 请求总数
	public static int threadTotal = 200; // 并发线程数
	public static volatile int count = 0; // 计数器
	private static final Logger LOGGER = LoggerFactory.getLogger(AtomicExample7.class);

	public static void main(String[] args) throws Exception {
		ExecutorService threadPool = Executors.newCachedThreadPool(); // 创建线程池
		final CountDownLatch countDownLatch = new CountDownLatch(clientTotal); // 计数器闭锁
		for (int i = 0; i < clientTotal; i++) {
			threadPool.execute(() -> {
				add();
				countDownLatch.countDown();
			});
		}
		countDownLatch.await();
		threadPool.shutdown();
		LOGGER.info("count:" + count); // 每次输出的值均不同, 小于预期值 5000
	}

	/**
	 * 这里实际是三步操作:
	 * 	从主存中读取 count 值
	 * 	对 count 执行 +1 操作
	 *  将 count 值写回主存
	 * 中间每一步都可能被其他线程打断
	 */
	private static void add() {
		count++;
	}
}
