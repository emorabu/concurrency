package com.aironi.concurrency.example.aqs;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class GCyclicBarrier {
	private static CyclicBarrier cyclicBarrier = new CyclicBarrier(5); // 达到5个后一起输出

	public static void main(String[] args) throws InterruptedException {
		ExecutorService threadPool = Executors.newCachedThreadPool();
		for (int i = 0; i < 10; i++) { // 总共10个任务, 每个等待1秒
			final int num = i;
			Thread.sleep(1000);
			threadPool.execute(() -> {
				try {
					race(num);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			});
		}
		threadPool.shutdown();
	}

	static void race(int i) throws InterruptedException {
		Thread.sleep(1000);
		System.out.println(i + " is ready");
		try {
			cyclicBarrier.await(2000, TimeUnit.MILLISECONDS); // java.util.concurrent.TimeoutException, java.util.concurrent.BrokenBarrierException
		} catch (BrokenBarrierException | TimeoutException e) {

		} finally {
			System.out.println(i + " continue");
		}
	}
}

/**
 *一种执行结果:
0 is ready
1 is ready
2 is ready
0 continue
1 continue
2 continue
3 is ready
3 continue
4 is ready
4 continue
5 is ready
5 continue
6 is ready
6 continue
7 is ready
7 continue
8 is ready
8 continue
9 is ready
9 continue

 */
