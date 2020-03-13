package com.aironi.concurrency.example.aqs;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HCyclicBarrier {
	private static CyclicBarrier cyclicBarrier = new CyclicBarrier(5,()->{System.out.println(11);}) ; // 第二个参数是 Runnable, 每次达到屏障点时先执行
	public static void main(String[] args) throws InterruptedException {
		ExecutorService threadPool = Executors.newCachedThreadPool();
		for(int i=0;i<10;i++) { // 总共10个任务, 每个等待1秒
			final int num = i;
			Thread.sleep(1000);
			threadPool.execute(()->{
				try {
					race(num);
				} catch (InterruptedException | BrokenBarrierException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			});
		}
		threadPool.shutdown();
	}
	static void race(int i) throws InterruptedException, BrokenBarrierException {
		Thread.sleep(1000);
		System.out.println(i+ " is ready");
		cyclicBarrier.await(); // 也可以指定时间
		System.out.println(i+" continue");
	}
}

/**
 *一种执行结果:
 0 is ready
1 is ready
2 is ready
3 is ready
4 is ready
4 continue
0 continue
1 continue
3 continue
2 continue
5 is ready
6 is ready
7 is ready
8 is ready
9 is ready
9 continue
5 continue
7 continue
6 continue
8 continue
 
 */
