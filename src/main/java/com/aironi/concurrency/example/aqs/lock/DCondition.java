package com.aironi.concurrency.example.aqs.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DCondition {
	public static void main(String[] args) {
		Lock lock = new ReentrantLock();
		Condition condition = lock.newCondition();
		new Thread(() -> {
			lock.lock();
			try {
				System.out.println("wait signal"); // 1
				condition.await();
				System.out.println("get signal"); // 4
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				lock.unlock();
			}
		}).start();
		new Thread(() -> {
			lock.lock();
			try {
				System.out.println("get lock"); // 2
				Thread.sleep(1000);
				condition.signalAll();
				System.out.println("send signal"); // 3
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				lock.unlock();
			}
		}).start();
	}
}
