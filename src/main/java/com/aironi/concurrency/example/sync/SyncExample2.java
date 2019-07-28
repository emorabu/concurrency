package com.aironi.concurrency.example.sync;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SyncExample2 {
	private static final Logger LOGGER = LoggerFactory.getLogger(SyncExample2.class);

	public static void main(String[] args) {
//		invoke1();
		invoke2();
	}

	public static void invoke1() {
		SyncExample2 example1 = new SyncExample2();
		SyncExample2 example2 = new SyncExample2();
		ExecutorService threadPool = Executors.newCachedThreadPool();
		threadPool.execute(() -> {
			example1.test1(1);
		});
		threadPool.execute(() -> {
			example2.test1(2);
		});
		// 两个线程抢夺资源, 各自输出递增
	}

	public static void invoke2() {
		SyncExample2 example1 = new SyncExample2();
		SyncExample2 example2 = new SyncExample2();
		ExecutorService threadPool = Executors.newCachedThreadPool();
		threadPool.execute(() -> {
			example1.test2(1);
		});
		threadPool.execute(() -> {
			example2.test2(2);
		});
		// 两个线程抢夺资源, 各自输出递增
	}

	// 修饰代码块
	public void test1(int j) {
		synchronized (this) {
			for (int i = 0; i < 10; i++) {
				LOGGER.info("test1: " + j + "..." + i);
			}
		}
	}

	// 修饰方法
	public synchronized void test2(int j) {
		for (int i = 0; i < 10; i++) {
			LOGGER.info("test1: " + j + "..." + i);
		}
	}
}
