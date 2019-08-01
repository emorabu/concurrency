package com.aironi.concurrency.example.syncContainer;

import java.util.Vector;

/**
 * 同步容器依然可能产生线程不安全的问题
 * @author emora
 *
 */
public class AVector {
	private static Vector<Integer> vector = new Vector<>();
	public static void main(String[] args) {
		while(true) {			
			for(int i=0;i<10;i++) {
				vector.add(i);
			}
			Thread t1 = new Thread(()->{
				for(int i=0;i<vector.size();i++) {
					vector.remove(i);
				}
			})  ;
			Thread t2 = new Thread(()->{
				for(int i=0;i<vector.size();i++) {
					vector.get(i);
				}
			});
			t1.start();
			t2.start();
		}
	}
}
