package com.aironi.concurrency.example.sync;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SyncExample1 {
	private static final Logger LOGGER = LoggerFactory.getLogger(SyncExample1.class);

	public static void main(String[] args) {
//		invoke1();
//		invoke2();
	}

	public static void invoke1() {
		SyncExample1 example1 = new SyncExample1();
		ExecutorService threadPool = Executors.newCachedThreadPool();
		threadPool.execute(() -> {
			example1.test1();
		});
		threadPool.execute(() -> {
			example1.test1();
		});
		// 第一个线程先持有锁, 输出 0 1 2 ... 9 后释放锁, 第二个线程再次输出 0 1 2 ... 9
	}

	public static void invoke2() {
		SyncExample1 example1 = new SyncExample1();
		ExecutorService threadPool = Executors.newCachedThreadPool();
		threadPool.execute(() -> {
			example1.test2();
		});
		threadPool.execute(() -> {
			example1.test2();
		});
		// 第一个线程先持有锁, 输出 0 1 2 ... 9 后释放锁, 第二个线程再次输出 0 1 2 ... 9
	}

	// 修饰代码块
	public void test1() {
		synchronized (this) {
			for (int i = 0; i < 10; i++) {
				LOGGER.info("test1: " + i);
			}
		}
	}

	// 修饰方法
	public synchronized void test2() {
		for (int i = 0; i < 10; i++) {
			LOGGER.info("test2: " + i);
		}
	}
}
