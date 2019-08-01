package com.aironi.concurrency.example.syncContainer;

import java.util.Vector;

public class BVector {
	private static Vector<Integer> vector = new Vector<>();
	public static void main(String[] args) {
		while(true) {			
			for(int i=0;i<10;i++) {
				vector.add(i);
			}
			Thread t1 = new Thread(BVector::remove)  ;
			Thread t2 = new Thread(BVector::get);
			t1.start();
			t2.start();
		}
	}
	
	static void remove() {
		for(int i=0;i<vector.size();i++) {
			vector.remove(i);
		}
	}
	static void get() {
		for(int i=0;i<vector.size();i++) {
			vector.get(i);
		}
	}
}
