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
	public static void main(String[] args) {
		HSingleton.getInstance();
	}
}
