package com.aironi.concurrency.example.singleton;

public class HSingleton {
	private HSingleton() {}
	public static HSingleton getInstance() {
		return Singleton.instance;
	}
	private static class Singleton{
		private static HSingleton instance = new HSingleton();
		static {
			System.out.println("-------");
		}
	}
	static {
		System.out.println(1);
	}
	public static void main(String[] args) {
	//	HSingleton.getInstance();
	}
}
