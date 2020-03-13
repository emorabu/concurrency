package com.aironi.concurrency.example.aqs.lock;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

public class HForkJoinTask extends RecursiveTask<Integer> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final int THRESHOLD = 2;
	private int start;
	private int end;

	public HForkJoinTask(int start, int end) {
		super();
		this.start = start;
		this.end = end;
	}

	@Override
	protected Integer compute() {
		int sum = 0;
		boolean canCompute = (end - start) <= THRESHOLD;
		if (canCompute) {
			for (int i = start; i <= end; i++) {
				sum += i;
			}
		} else {
			int middle = (start + end) / 2;
			HForkJoinTask leftTask = new HForkJoinTask(start, middle);
			HForkJoinTask rightTask = new HForkJoinTask(middle + 1, end);

			// 执行子任务
			leftTask.fork();
			rightTask.fork();

			// 等待执行, 合并结果
			Integer leftResult = leftTask.join();
			Integer rightResult = rightTask.join();
			sum += leftResult + rightResult;
		}
		return sum;
	}
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		ForkJoinPool pool = new ForkJoinPool();
		HForkJoinTask task = new HForkJoinTask(1, 100);
		ForkJoinTask<Integer> joinTask = pool.submit(task);
		System.out.println(joinTask.get());
		
	}
}
