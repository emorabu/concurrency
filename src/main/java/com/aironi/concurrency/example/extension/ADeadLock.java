package com.aironi.concurrency.example.extension;

public class ADeadLock implements Runnable {
	public boolean flag = true; // flag 为成员变量是为了保证每个线程都能先持有到第一个锁
	private static Object o1 = new Object(); // 两个锁为静态变量
	private static Object o2 = new Object();

	@Override
	public void run() {
		if (flag) {
			synchronized (o1) {
				System.out.println("11...");
				synchronized (o2) {
					System.out.println("12...");
				}
			}
		} else {
			synchronized (o2) {
				System.out.println("22...");
				synchronized (o1) {
					System.out.println("21...");
				}
			}
		}
	}

	public static void main(String[] args) {
		ADeadLock a1 = new ADeadLock();
		ADeadLock a2 = new ADeadLock();
		a1.flag=true;
		a1.flag = false;
		new Thread(a1).start();
		new Thread(a2).start();
	}

}
