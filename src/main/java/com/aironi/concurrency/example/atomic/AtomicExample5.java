package com.aironi.concurrency.example.atomic;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aironi.concurrency.annotations.ThreadSafe;

@ThreadSafe
public class AtomicExample5 {
	private static final Logger LOGGER = LoggerFactory.getLogger(AtomicExample5.class);

	// 第二个参数为字段名称, 该字段必须使用 volatile 标记, 且不能是 static 的
	private static AtomicIntegerFieldUpdater<AtomicExample5> updater = AtomicIntegerFieldUpdater
			.newUpdater(AtomicExample5.class, "count");
	private volatile int count = 100;

	public static void main(String[] args) {
		AtomicExample5 example5 = new AtomicExample5();
		if (updater.compareAndSet(example5, 100, 200)) {
			LOGGER.info("success count:" + example5.getCount());
		}
		if (updater.compareAndSet(example5, 100, 200)) {
			LOGGER.info("success count:" + example5.getCount());
		} else {
			LOGGER.info("fail count:" + example5.getCount());
		}
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

}
