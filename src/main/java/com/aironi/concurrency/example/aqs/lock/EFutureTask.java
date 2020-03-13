package com.aironi.concurrency.example.aqs.lock;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class EFutureTask {
	private static class MyCallable implements Callable<String>{

		@Override
		public String call() throws Exception {
			System.out.println("do something...");
			Thread.sleep(2000);
			return "done";
		}
		
	}
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		ExecutorService threadPool = Executors.newCachedThreadPool();
		Future<String> future = threadPool.submit(new MyCallable());
		System.out.println("-----------------");
		Thread.sleep(1000);;
		System.out.println(future.get());
		threadPool.shutdown();
		System.out.println("++++++++++++++++++++");
	}
}
