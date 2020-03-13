package com.aironi.concurrency.example.threadpool;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class DScheduledThreadPool {
	public static void main(String[] args) {
		 ScheduledExecutorService threadPool = Executors.newScheduledThreadPool(5);
		 threadPool.scheduleAtFixedRate(()->System.out.println("fixed rate"), 1, 1, TimeUnit.SECONDS); // 注意: 起始时间延迟和周期都是 long
		threadPool.schedule(()->System.out.println("hello"), 2, TimeUnit.SECONDS); // 延迟2秒执行
//		threadPool.shutdown(); // ScheduledExecutorService 用于调度, 所以不要直接关闭线程池
		
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			
			@Override
			public void run() {
				System.out.println("timer run...");
			}
		}, 1000); // 1秒后执行
	}
}
