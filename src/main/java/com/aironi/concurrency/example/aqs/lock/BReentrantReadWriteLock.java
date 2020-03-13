package com.aironi.concurrency.example.aqs.lock;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;


public class BReentrantReadWriteLock {
	private final Map<String, Data> map = new TreeMap<>();
	private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
	private final Lock readLock = lock.readLock();
	private final Lock writeLock = lock.writeLock();
	
	public Data get(String key) {
		readLock.lock();
		try {
			return map.get(key);
		}finally {
			readLock.unlock();
		}
	}
	public Set<String> getAllKeys(){
		readLock.lock();
		try {
			return map.keySet();
		}finally {
			readLock.unlock();
		}
	}
	
	public Data put(String key, Data value) {
		writeLock.lock(); // 要获取写入锁时, 坚决不允许任何线程还持有读锁. 即所有读操作完成了, 才能执行写入.
		try {
			return map.put(key, value);
		}finally {
			writeLock.unlock();
		}
	}
	class Data{
		
	}
}
