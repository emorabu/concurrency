package com.aironi.concurrency.example.aqs.lock;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class GFutureTask {
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		FutureTask<String> task = new FutureTask<>(()->{
			System.out.println("do something...");
			Thread.sleep(2000);
			return "done";
		});
		
		new Thread(task).start();
		System.out.println("do something in main.....");
		Thread.sleep(1000);
		String result = task.get();
		System.out.println(result);
	}
}
