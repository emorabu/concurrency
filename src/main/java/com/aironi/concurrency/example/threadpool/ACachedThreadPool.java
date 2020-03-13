package com.aironi.concurrency.example.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ACachedThreadPool {
	public static void main(String[] args) {
		ExecutorService threadPool = Executors.newCachedThreadPool();
		for(int i=0;i<10;i++) {
			final int num = i;
			threadPool.execute(()->{
				System.out.println(num);
			});
		}
		threadPool.shutdown();
	}
}
