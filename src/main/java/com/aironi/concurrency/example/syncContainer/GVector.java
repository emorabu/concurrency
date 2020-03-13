package com.aironi.concurrency.example.syncContainer;

import java.util.Iterator;
import java.util.Vector;

/**
 * 同步容器单线程增删依然可能有问题
 * 推荐做法: 不要在循环时删除元素, 而应在循环时做好标记, 循环完后操作
 * @author emora
 *
 */
public class GVector {
	/**
	 * java.util.ConcurrentModificationException
	 * @param v1
	 */
	static void test1(Vector<Integer> v1) {
		for (Integer i : v1) {
			if (i.equals(3)) {
				v1.remove(i);
			}
		}
	}

	/**
	 * java.util.ConcurrentModificationException
	 * @param v1
	 */
	static void test2(Vector<Integer> v1) {
		Iterator<Integer> iterator = v1.iterator();
		while (iterator.hasNext()) {
			Integer i = iterator.next();
			if (i.equals(3)) {
//				iterator.remove();
				v1.remove(i);
			}
		}
	}

	/**
	 * 成功
	 * 推荐使用
	 * @param v1
	 */
	 static void test3(Vector<Integer> v1) {
		for (int i = 0; i < v1.size(); i++) {
			if (v1.get(i).equals(3)) {
				v1.remove(i);
			}
		}
	}

	public static void main(String[] args) {
		Vector<Integer> v = new Vector<>();
		v.add(1);
		v.add(2);
		v.add(3);
		
		test3(v);
	}
}
